package com.ttaengju.websocket.ai.app.endpoint;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import com.ttaengju.websocket.ai.app.endpoint.handler.ChatConversationHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@Slf4j
public class ChatConversationRouters {

    @Bean
    public RouterFunction<ServerResponse> chatConversationRoute(
            ChatConversationHandler chatConversationHandler) {
        return RouterFunctions.route().path("/llm",
                config -> config.nest(accept(MediaType.APPLICATION_JSON),
                        builder -> builder.GET("/chat/conversation",
                                        chatConversationHandler::getChatConversation)
                                .POST("/test/chat/conversation",
                                        chatConversationHandler::getChatConversation)

                )).after((req, res) -> {
            log.info("request!! : {}", req);
            log.info("response!! : {}", res);
            return res;
        }).build();
    }

}
