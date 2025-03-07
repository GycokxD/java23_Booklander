package kg.attractor.java.lesson45;

import com.sun.net.httpserver.HttpExchange;
import kg.attractor.java.lesson44.*;
import kg.attractor.java.lesson46.AuthUtils;
import kg.attractor.java.lesson46.SessionManager;
import kg.attractor.java.server.ContentType;
import kg.attractor.java.server.Cookie;
import kg.attractor.java.server.ResponseCodes;
import kg.attractor.java.utils.DataLoader;
import kg.attractor.java.utils.DataSaver;
import kg.attractor.java.utils.Utils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Lesson45Server extends Lesson44Server {

    private List<Employee> employees = new ArrayList<>();
    private List<Book> books;
    private List<BookLoan> bookLoans;
    private List<Genre> genres;

    private Employee currentUser = null;


    public Lesson45Server(String host, int port) throws IOException {
        super(host, port);

        try {
            this.books = DataLoader.loadBooks("data/json/books.json");
            this.employees = DataLoader.loadEmployees("data/json/employees.json");
            this.bookLoans = DataLoader.loadBookLoans("data/json/bookLoans.json");
            this.genres = DataLoader.loadGenres("data/json/genres.json");
        } catch (Exception e) {
            System.err.println("Ошибка при загрузке данных: " + e.getMessage());
            throw new RuntimeException(e);
        }

        registerGet("/auth/login", this::loginGet);
        registerPost("/auth/login", this::loginPost);

        registerGet("/register", this::registerGet);
        registerPost("/register", this::registerPost);

        registerGet("/login", this::loginGet);
        registerPost("/login", this::loginPost);
        registerGet("/profile", this::profileGet);
    }


    private void loginGet(HttpExchange exchange) {
        Map<String, Object> data = new HashMap<>();
        renderTemplate(exchange, "/login/login.ftlh", data);
    }

    private void registerGet(HttpExchange exchange) {
        Path path = makeFilePath("/reg/register.ftlh");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }

    private void registerPost(HttpExchange exchange) {
        String raw = getBody(exchange);
        Map<String, String> parsed = Utils.parseUrlEncoded(raw, "&");

        String id = parsed.get("id");
        String name = parsed.get("name");
        String email = parsed.get("email");
        String password = parsed.get("password");

        if (isBlank(id) || isBlank(name) || isBlank(email) || isBlank(password)) {
            sendResponse(exchange, "Ошибка: Все поля должны быть заполнены.");
            return;
        }

        if (containsWhitespace(id)) {
            sendResponse(exchange, "Ошибка: ID не должен содержать пробелов.");
            return;
        }

        if (containsWhitespace(name)) {
            sendResponse(exchange, "Ошибка: Имя не должно содержать пробелов.");
            return;
        }

        boolean isRegistered = employees.stream().anyMatch(e -> e.getEmail().equals(email));

        if (isRegistered) {
            sendResponse(exchange, "Ошибка: Пользователь с таким email уже существует.");
        } else {
            Employee newEmployee = new Employee(id, name, email, password);
            employees.add(newEmployee);
            redirect303(exchange, "/login");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private boolean containsWhitespace(String value) {
        return value != null && value.contains(" ");
    }

    private void loginPost(HttpExchange exchange) {
        String raw = getBody(exchange);
        Map<String, String> parsed = Utils.parseUrlEncoded(raw, "&");

        String email = parsed.get("email");
        String password = parsed.get("password");

        Optional<Employee> userOpt = employees.stream()
                .filter(e -> e.getEmail().equals(email) && e.getPassword().equals(password))
                .findFirst();

        if (userOpt.isPresent()) {
            Employee employee = userOpt.get();
            String sessionId = AuthUtils.generateSessionId();
            SessionManager.addSession(sessionId, employee);

            Cookie sessionCookie = Cookie.make("sessionId", sessionId);
            sessionCookie.setMaxAge(600);
            sessionCookie.setHttpOnly(true);
            setCookie(exchange, sessionCookie);

            redirect303(exchange, "/profile");
        } else {
            Map<String, Object> data = new HashMap<>();
            data.put("error", true);
            renderTemplate(exchange, "/login/login.ftlh", data);
        }
    }

    private void profileGet(HttpExchange exchange) {
        Employee user = getAuthenticatedUser (exchange);
        if (user == null) {
            redirect303(exchange, "/login");
            return;
        }

        Map<String, Object> data = new HashMap<>();
        data.put("user", user);
        data.put("borrowedBooks", user.getBorrowedBooks());
        data.put("books", books);
        renderTemplate(exchange, "/profile/profile.ftlh", data);
    }
}
