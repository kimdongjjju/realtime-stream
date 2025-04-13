package com.ttaengju.websocket.ai.app.endpoint.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatConversationResponse {

    private String name;

    private String answer;

}
