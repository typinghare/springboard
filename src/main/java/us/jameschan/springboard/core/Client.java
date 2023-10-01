package us.jameschan.springboard.core;

import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

public class Client {
    private final WebSocketSession session;

    private final Authentication authentication = new Authentication();

    private final Set<Client> subscriberSet = new HashSet<>();

    public Client(WebSocketSession session) {
        this.session = session;
    }

    public WebSocketSession getSession() {
        return session;
    }

    public void addSubscriber(Client client) {
        this.subscriberSet.add(client);
    }

    public void removeSubscriber(Client client) {
        this.subscriberSet.remove(client);
    }

    public Set<Client> getSubscriberSet() {
        return subscriberSet;
    }

    public Authentication getAuthentication() {
        return authentication;
    }
}
