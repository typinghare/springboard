package us.jameschan.springboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import us.jameschan.springboard.core.SpringboardWebsocketHandler;

/**
 * Register WebSocket handlers here.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final SpringboardWebsocketHandler springboardWebsocketHandler;

    @Autowired
    public WebSocketConfig(SpringboardWebsocketHandler springboardWebsocketHandler) {
        this.springboardWebsocketHandler = springboardWebsocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(springboardWebsocketHandler, "/").setAllowedOrigins("*");
    }
}
