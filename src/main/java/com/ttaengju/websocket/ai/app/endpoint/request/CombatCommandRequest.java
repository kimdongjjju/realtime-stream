package com.ttaengju.websocket.ai.app.endpoint.request;

import com.ttaengju.websocket.ai.domain.Combat;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class CombatCommandRequest {

    private String id;
    private String command;

    public Combat toDomain() {
        return Combat.builder()
                .id(id)
                .command(command)
                .build();
    }
}
