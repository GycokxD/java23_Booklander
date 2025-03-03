package kg.attractor.java.lesson46;

import com.sun.net.httpserver.HttpExchange;
import kg.attractor.java.lesson44.Employee;
import kg.attractor.java.lesson45.Lesson45Server;
import kg.attractor.java.server.Cookie;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lesson46 extends Lesson45Server {
    public Lesson46(String host, int port) throws IOException {
        super(host, port);

        registerGet("/cookie", this::cookieHandler);
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

    private Employee getAuthenticatedUser(HttpExchange exchange) {
        String cookies = getCookies(exchange);
        Map<String, String> cookieMap = Cookie.parse(cookies);
        String sessionId = cookieMap.get("sessionId");

        if (sessionId != null) {
            return SessionManager.getEmployee(sessionId);
        }
        return null;
    }
}
