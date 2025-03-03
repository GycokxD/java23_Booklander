package kg.attractor.java.lesson46;

import java.util.concurrent.ThreadLocalRandom;

public class AuthUtils {
    public static String generateSessionId() {
        String sessionId;
        do {
            long timePart = System.currentTimeMillis();
            int randomPart = ThreadLocalRandom.current().nextInt(1000);
            sessionId = timePart + "-" + randomPart;
        } while (SessionManager.containsSession(sessionId));
        return sessionId;
    }
}