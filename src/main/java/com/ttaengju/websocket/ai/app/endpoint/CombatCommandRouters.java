package com.ttaengju.websocket.ai.app.endpoint;

import com.ttaengju.websocket.ai.app.endpoint.handler.CombatCommandHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Slf4j
@Configuration
public class CombatCommandRouters {

    @Bean
    public RouterFunction<?> routerCombatCommand(CombatCommandHandler handler) {
        return route()
                .POST("/combat-command",handler::createCombatCommand)
                .GET("/combat-command/{id}",handler::getCombatCommand)
                .build();
    }
}
