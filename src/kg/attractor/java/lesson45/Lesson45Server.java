package kg.attractor.java.lesson45;

import com.sun.net.httpserver.HttpExchange;
import kg.attractor.java.lesson44.*;
import kg.attractor.java.server.ContentType;
import kg.attractor.java.server.ResponseCodes;
import kg.attractor.java.utils.DataLoader;
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

        boolean isRegistered = employees.stream().noneMatch(e -> e.getEmail().equals(email));

        String response;
        if (isRegistered) {
            employees.add(new Employee(id, name, email, password));
            response = "<div class='success-message'><h1>Удачная регистрация!</h1></div>";
        } else {
            response = "<div class='error-message'><h1>Регистрация не удалась. Пользователь с таким email уже существует.</h1>" +
                    "<a href='/register'>Попробовать снова</a></div>";
        }

        try {
            sendByteData(exchange, ResponseCodes.OK, ContentType.TEXT_HTML, response.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            currentUser = userOpt.get();
            redirect303(exchange, "/profile");
        } else {
            Map<String, Object> data = new HashMap<>();
            data.put("error", true);
            renderTemplate(exchange, "/login/login.ftlh", data);
        }
    }

    private void profileGet(HttpExchange exchange) {
        Map<String, Object> data = new HashMap<>();
        if (currentUser != null) {
            data.put("user", currentUser);
        } else {
            data.put("user", new Employee("0", "Некий пользователь", "Неизвестно", ""));
        }
        renderTemplate(exchange, "/profile/profile.ftlh", data);
    }
}
