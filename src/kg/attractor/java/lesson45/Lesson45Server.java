package kg.attractor.java.lesson45;

import com.sun.net.httpserver.HttpExchange;
import kg.attractor.java.lesson44.Employee;
import kg.attractor.java.lesson44.Lesson44Server;
import kg.attractor.java.server.ContentType;
import kg.attractor.java.server.ResponseCodes;
import kg.attractor.java.utils.Utils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Lesson45Server extends Lesson44Server {

    private List<Employee> employees = new ArrayList<>();


    public Lesson45Server(String host, int port) throws IOException {
        super(host, port);

        registerGet("/auth/login", this::loginGet);
        registerPost("/auth/login", this::loginPost);

        registerGet("/register", this::registerGet);
        registerPost("/register", this::registerPost);
    }

    private void loginPost(HttpExchange exchange) {
        String cType = exchange.getRequestHeaders()
                .getOrDefault("Content-Type", List.of())
                .get(0);

        String raw = getBody(exchange);

        Map<String, String> parsed = Utils.parseUrlEncoded(raw, "&");
        String fmt = "<p>Необработанные данные: <b>%s</b></p>" +
                "<p>Content-Type: <b>%s</b></p>" +
                "<p>После обработки: <b>%s</b></p>";
        String data = String.format(fmt, raw, cType, parsed);

        try{
            sendByteData(exchange, ResponseCodes.OK, ContentType.TEXT_HTML, data.getBytes());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loginGet(HttpExchange exchange) {
        Path path = makeFilePath("/auth/login.ftlh");
        sendFile(exchange, path, ContentType.TEXT_HTML);
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
            employees.add(new Employee(id, name, email));
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
}
