package com.wyjax.shutup.common.config;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Slf4j
public class SocketTextHandler extends TextWebSocketHandler {
    private final Set<WebSocketSession> sessions = new ConcurrentSkipListSet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("connection closed id: {}", session.getId());
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        JSONObject jsonObject = new JSONObject(payload);
        for (WebSocketSession socketSession : sessions) {
            socketSession.sendMessage(new TextMessage("welcome" + jsonObject.getString("user")));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("connection closed id: {}", session.getId());
        sessions.remove(session);
    }
}
