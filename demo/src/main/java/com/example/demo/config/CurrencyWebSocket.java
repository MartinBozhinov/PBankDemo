package com.example.demo.config;

import com.example.demo.domain.currency.service.CurrencyService;
import com.google.gson.Gson;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class CurrencyWebSocket implements WebSocketHandler {

    private final Gson gson = new Gson();
    private CurrencyService currencyService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        currencyService.addSession(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
