package com.ttaengju.websocket.ai.app.endpoint;

import com.ttaengju.websocket.ai.app.endpoint.handler.ChatHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@Slf4j
public class ChatRouters {

    @Bean
    public RouterFunction<ServerResponse> chatRoute(ChatHandler chatHandler) {
        return RouterFunctions.route()
                .path("/chat", config -> config
                        .nest(RequestPredicates.accept(MediaType.APPLICATION_JSON), builder -> builder
                                .POST("/connection/{id}", chatHandler::connectionChatting)
                                .GET("/users", RequestPredicates.accept(MediaType.APPLICATION_JSON), chatHandler::getUserNameBySession)
                        )
                )
                .after((req, res) -> {
                    log.info("Request: {} {}", req.methodName(), req.path());
                    log.info("Response status: {}", res.statusCode());
                    return res;
                })
                .build();
    }


}
