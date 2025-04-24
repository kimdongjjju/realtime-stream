package com.ttaengju.websocket.ai.app.endpoint.handler;

import com.ttaengju.websocket.ai.app.endpoint.request.CombatCommandRequest;
import com.ttaengju.websocket.ai.domain.CombatDomainService;
import com.ttaengju.websocket.ai.domain.OpenAiCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class CombatCommandHandler {

    private final CombatDomainService combatDomainService;
    private final OpenAiCommandService openAiCommandService;

    public Mono<ServerResponse> createCombatCommand(ServerRequest request) {
        return request.bodyToMono(CombatCommandRequest.class)
                .map(it -> it.toDomain())
                .map(combat -> combatDomainService.findCombatById(combat.getId()))
                .flatMap(c -> ServerResponse.ok().bodyValue(openAiCommandService.chatConversation(c.getCommand())));
    }

    public Mono<ServerResponse> getCombatCommand(ServerRequest request) {

        return ServerResponse.ok().build();
    }

}
