package com.ttaengju.websocket.ai.app.endpoint.handler;


import com.ttaengju.websocket.ai.app.endpoint.request.ChatConversationRequest;
import com.ttaengju.websocket.ai.app.endpoint.response.ChatConversationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatConversationHandler {


    public Mono<ServerResponse> getChatConversation(ServerRequest request) {

        return request.bodyToMono(ChatConversationRequest.class)
                .flatMap(chatRequest -> {



                    ChatConversationResponse response = ChatConversationResponse.builder()
                            .name(chatRequest.getName())
                            .answer("Processed answer")
                            .build();

                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromValue(response));
                });
    }

    public Mono<ServerResponse> updateChatConversation(ServerRequest request) {
        return request.body(BodyExtractors.toMono(ChatConversationRequest.class))
                .doOnNext(chatConversationRequest -> log.info("request : {}", chatConversationRequest))
                .flatMap(chatConversationRequest -> {
                    ChatConversationResponse response = ChatConversationResponse.builder()
                            .name(chatConversationRequest.getName())
                            .answer("Processed answer")
                            .build();

                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromValue(response));
                });
    }

}
