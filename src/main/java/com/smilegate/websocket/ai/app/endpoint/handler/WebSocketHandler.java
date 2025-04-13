package com.smilegate.websocket.ai.app.endpoint.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class WebSocketHandler implements org.springframework.web.reactive.socket.WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        String sessionId = UriComponentsBuilder
                .fromUri(session.getHandshakeInfo().getUri())
                .build()
                .getQueryParams()
                .getFirst("sessionId");

        log.info("WebSocket 연결됨 - 세션 ID: {}", sessionId);

        return session.receive()
                .doOnNext(msg -> {
                    String payload = msg.getPayloadAsText();
                    log.info("수신된 메시지: {}", payload);
                })
                .map(msg -> session.textMessage("Echo: " + msg.getPayloadAsText()))
                .as(session::send);
    }
}

