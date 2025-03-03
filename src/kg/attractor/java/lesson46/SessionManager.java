package kg.attractor.java.lesson46;

import kg.attractor.java.lesson44.Employee;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    private static final Map<String, Employee> sessions = new HashMap<>();

    public static void addSession(String sessionId, Employee employee) {
        sessions.put(sessionId, employee);
    }

    public static Employee getEmployee(String sessionId) {
        return sessions.get(sessionId);
    }

    public static void removeSession(String sessionId) {
        sessions.remove(sessionId);
    }

    public static boolean containsSession(String sessionId) {
        return sessions.containsKey(sessionId);
    }
}