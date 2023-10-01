package us.jameschan.springboard.core;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class SessionManager {
    private final Map<WebSocketSession, Client> sessionClientMap = new HashMap<>();

    public Client addSession(WebSocketSession session) {
        if (sessionClientMap.containsKey(session)) {
            return null;
        }

        final Client client = new Client(session);
        sessionClientMap.put(session, client);

        return client;
    }

    public Client getClient(WebSocketSession session) {
        return sessionClientMap.get(session);
    }

    public Client removeSession(WebSocketSession session) {
        if (!sessionClientMap.containsKey(session)) {
            return null;
        }

        return sessionClientMap.remove(session);
    }

    public Collection<Client> getAllClient() {
        return sessionClientMap.values();
    }
}
