package us.jameschan.springboard.core;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class SpringboardWebsocketHandler implements WebSocketHandler {
    private final SessionManager sessionManager;

    private final ControlCenter controlCenter;

    private final Gson gson;

    public SpringboardWebsocketHandler(SessionManager sessionManager, ControlCenter controlCenter, Gson gson) {
        this.sessionManager = sessionManager;
        this.controlCenter = controlCenter;
        this.gson = gson;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        final Client client = sessionManager.addSession(session);
        final Action requestSignInAction = new Action("request-sign-in");

        controlCenter.forward(client, requestSignInAction);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
        final String text = (String) message.getPayload();
        final Client client = sessionManager.getClient(session);
        final ActionPackage actionPackage = gson.fromJson(text, ActionPackage.class);

        controlCenter.receive(client, actionPackage);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
