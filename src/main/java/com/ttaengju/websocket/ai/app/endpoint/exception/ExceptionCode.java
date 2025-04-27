package com.ttaengju.websocket.ai.app.endpoint.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ExceptionCode {
    private final HttpStatus status;
    private final String code;
    private final String message;
}

