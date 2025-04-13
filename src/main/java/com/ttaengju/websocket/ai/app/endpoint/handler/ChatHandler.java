package com.ttaengju.websocket.ai.app.endpoint.handler;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import java.net.URI;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatHandler {

    private final Map<String, String> sessionMap = new ConcurrentHashMap<>();


    public Mono<ServerResponse> connectionChatting(ServerRequest request) {
        String userId = request.pathVariable("id");
        String sessionId = UUID.randomUUID().toString();
        sessionMap.put(sessionId, userId);

        URI wsUri = URI.create("ws://localhost:8080/ai-connection?sessionId=" + sessionId);
        TcpClient tcpClient = TcpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(5))
                );

        ReactorNettyWebSocketClient client = new ReactorNettyWebSocketClient(
                HttpClient.from(tcpClient)
        );

        return client.execute(wsUri, session ->
                session.send(Mono.empty())
                        .then()
        ).then(
                ServerResponse.ok().bodyValue(Map.of(
                        "status", "connected",
                        "sessionId", sessionId,
                        "websocketUrl", wsUri.toString()
                ))
        ).onErrorResume(e -> {
            log.error("WebSocket 연결 실패", e);
            return ServerResponse.status(500).bodyValue("WebSocket 연결 실패: " + e.getMessage());
        });
    }




    public Mono<ServerResponse> getUserNameBySession(ServerRequest request) {
        String sessionId = request.queryParam("sessionId").orElse(null);
        return ServerResponse.ok().bodyValue(Optional.ofNullable(sessionMap.get(sessionId)));
    }


}
