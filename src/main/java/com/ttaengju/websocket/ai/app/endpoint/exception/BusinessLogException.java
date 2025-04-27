package com.ttaengju.websocket.ai.app.endpoint.exception;

import lombok.Getter;

@Getter
public class BusinessLogException extends RuntimeException {

    private final ExceptionCode errorCode;

    public BusinessLogException(ExceptionCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}

