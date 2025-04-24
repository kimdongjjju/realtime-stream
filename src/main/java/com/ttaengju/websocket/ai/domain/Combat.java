package com.ttaengju.websocket.ai.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Combat {
    private String id;

    private String command;

    private String job;

    private String action;
}
