package kg.attractor.java.lesson46;

import com.sun.net.httpserver.HttpExchange;
import kg.attractor.java.lesson44.Book;
import kg.attractor.java.lesson44.Employee;
import kg.attractor.java.lesson45.Lesson45Server;
import kg.attractor.java.server.Cookie;
import kg.attractor.java.utils.DataException;
import kg.attractor.java.utils.DataLoader;
import kg.attractor.java.utils.DataSaver;
import kg.attractor.java.utils.Utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class Lesson46 extends Lesson45Server {
    private List<Book> books;
    private List<Employee> employees;

    public Lesson46(String host, int port) throws IOException {
        super(host, port);

        try {
            this.books = DataLoader.loadBooks("data/json/books.json");
        } catch (Exception e) {
            System.err.println("Ошибка при загрузке книг: " + e.getMessage());
            this.books = new ArrayList<>();
        }

        registerGet("/cookie", this::cookieHandler);
        registerGet("/book-actions", this::bookActionsHandler);
        registerGet("/borrow", this::borrowBookPage);
        registerGet("/return", this::returnBookPage);
        registerGet("/borrow/{bookId}", this::confirmBorrow);
        registerGet("/return/{bookId}", this::confirmReturn);
        registerGet("/logout", this::logoutPage);
        registerPost("/borrow", this::borrowBook);
        registerPost("/return", this::returnBook);
        registerPost("/logout", this::logout);
    }

    private void cookieHandler(HttpExchange exchange) {
        Map<String, Object> data = new HashMap<>();
        String name = "times";
//        int times = 42;
//        data.put("times", times);

        Cookie sessionCookie = Cookie.make("userId", "123");
        setCookie(exchange, sessionCookie);

        Cookie c1 = Cookie.make("user%Id", "456");
        setCookie(exchange, c1);

        Cookie c2 = Cookie.make("restricted()<>@,;:\\\"/[]?={}", "()<>@,;:\\\"/[]?={}");
        setCookie(exchange, c2);

        Cookie c3 = Cookie.make("user-mail", "example@mail");
        setCookie(exchange, c3);

        String cookieString = getCookies(exchange);

        Map<String, String> cookies = Cookie.parse(cookieString);
        String cookieValue = cookies.getOrDefault(name, "0");
        int times = Integer.parseInt(cookieValue) + 1;
        Cookie response = new Cookie<>(name, times);
        setCookie(exchange, response);
        data.put(name, times);

        data.put("cookies", cookies);

        renderTemplate(exchange, "cookie/cookie.ftlh", data);
    }

    private void bookActionsHandler(HttpExchange exchange) {
        Employee user = getAuthenticatedUser(exchange);
        if (user == null) {
            redirect303(exchange, "/login");
            return;
        }

        Map<String, Object> data = new HashMap<>();
        data.put("books", books);
        renderTemplate(exchange, "books-actions/book-actions.ftlh", data);
    }

    private void borrowBookPage(HttpExchange exchange) {
        Employee user = getAuthenticatedUser(exchange);
        if (user == null) {
            redirect303(exchange, "/login");
            return;
        }

        Map<String, Object> data = new HashMap<>();
        data.put("books", books);
        renderTemplate(exchange, "books-actions/borrow-book.ftlh", data);
    }

    private void returnBookPage(HttpExchange exchange) {
        Employee user = getAuthenticatedUser(exchange);
        if (user == null) {
            redirect303(exchange, "/login");
            return;
        }

        Map<String, Object> data = new HashMap<>();
        data.put("books", books);
        renderTemplate(exchange, "books-actions/return-book.ftlh", data);
    }

    private void confirmBorrow(HttpExchange exchange) {
        Employee user = getAuthenticatedUser(exchange);
        if (user == null) {
            redirect303(exchange, "/login");
            return;
        }

        String bookId = exchange.getRequestURI().getPath().split("/")[2];
        Optional<Book> bookOpt = books.stream()
                .filter(b -> b.getId().equals(bookId) && !b.getBorrowed())
                .findFirst();

        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            book.setBorrowed(true);
            user.borrowBook(bookId);
            redirect303(exchange, "/book-actions");
        } else {
            sendResponse(exchange, "Книга недоступна для выдачи.");
        }
    }

    private void confirmReturn(HttpExchange exchange) {
        Employee user = getAuthenticatedUser(exchange);
        if (user == null) {
            redirect303(exchange, "/login");
            return;
        }

        String bookId = exchange.getRequestURI().getPath().split("/")[2];
        Optional<Book> bookOpt = books.stream()
                .filter(b -> b.getId().equals(bookId) && b.getBorrowed())
                .findFirst();

        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            book.setBorrowed(false);
            user.getBorrowedBooks().remove(bookId);
            redirect303(exchange, "/book-actions");
        } else {
            sendResponse(exchange, "Книга не найдена или уже возвращена.");
        }
    }

    private void borrowBook(HttpExchange exchange) {
        Employee user = getAuthenticatedUser (exchange);
        if (user == null) {
            redirect303(exchange, "/login");
            return;
        }

        String bookId = getBody(exchange).split("=")[1];
        Optional<Book> bookOpt = books.stream()
                .filter(b -> b.getId().equals(bookId) && !b.getBorrowed())
                .findFirst();

        if (bookOpt.isPresent()) {
            if (user.getBorrowedBooks().size() >= 2) {
                sendResponse(exchange, "Ошибка: Вы не можете взять больше 2 книг одновременно.");
                return;
            }

            Book book = bookOpt.get();
            book.setBorrowed(true);
            user.borrowBook(bookId);

            try {
                employees = DataLoader.loadEmployees("data/json/employees.json");
                DataSaver.saveBooks("data/json/books.json", books);
                DataSaver.saveEmployees("data/json/employees.json", employees);
            } catch (DataException | IOException e) {
                e.printStackTrace();
                sendResponse(exchange, "Ошибка при сохранении данных.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            redirect303(exchange, "/book-actions");
        } else {
            sendResponse(exchange, "Книга недоступна для выдачи.");
        }
    }

    private void returnBook(HttpExchange exchange) {
        Employee user = getAuthenticatedUser (exchange);
        if (user == null) {
            redirect303(exchange, "/login");
            return;
        }

        String bookId = getBody(exchange).split("=")[1];
        Optional<Book> bookOpt = books.stream()
                .filter(b -> b.getId().equals(bookId) && b.getBorrowed())
                .findFirst();

        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            book.setBorrowed(false);
            user.getBorrowedBooks().remove(bookId);

            try {
                employees = DataLoader.loadEmployees("data/json/employees.json");
                DataSaver.saveBooks("data/json/books.json", books);
                DataSaver.saveEmployees("data/json/employees.json", employees);
            } catch (DataException | IOException e) {
                e.printStackTrace();
                sendResponse(exchange, "Ошибка при сохранении данных.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            redirect303(exchange, "/book-actions");
        } else {
            sendResponse(exchange, "Книга не найдена или уже возвращена.");
        }
    }

    private void logout(HttpExchange exchange) {
        Employee user = getAuthenticatedUser (exchange);
        if (user != null) {
            String cookies = getCookies(exchange);
            Map<String, String> cookieMap = Cookie.parse(cookies);
            String sessionId = cookieMap.get("sessionId");

            if (sessionId != null) {
                SessionManager.removeSession(sessionId);
            }

            Cookie cookie = Cookie.make("sessionId", "");
            cookie.setMaxAge(0);
            setCookie(exchange, cookie);
        }

        redirect303(exchange, "/login");
    }

    private void logoutPage(HttpExchange exchange) {
        renderTemplate(exchange, "logout/logout.ftlh", null);
    }
}
