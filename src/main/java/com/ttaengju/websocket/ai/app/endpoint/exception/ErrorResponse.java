package com.ttaengju.websocket.ai.app.endpoint.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final int status;
    private final String message;

    public static ErrorResponse of(HttpStatus status, String message) {
        return new ErrorResponse(status.value(), message);
    }

    public static ErrorResponse of(int status, String message) {
        return new ErrorResponse(status, message);
    }
}

