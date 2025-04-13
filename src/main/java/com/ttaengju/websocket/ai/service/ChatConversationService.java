package com.ttaengju.websocket.ai.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatConversationService {

    public Flux<String> getChatConversation() {
        Flux.just("","");
        return Flux.empty();
    }

}
