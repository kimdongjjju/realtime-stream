package com.ttaengju.websocket.ai.app.endpoint.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(-2)
@Configuration
public class GlobalWebExceptionHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper;

    public GlobalWebExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        return handleException(exchange, ex);
    }

    private Mono<Void> handleException(ServerWebExchange exchange, Throwable throwable) {
        ErrorResponse errorResponse;
        DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        if (throwable instanceof BusinessLogException) {
            BusinessLogException ex = (BusinessLogException) throwable;
            ExceptionCode exceptionCode = ex.getErrorCode();
            errorResponse = ErrorResponse.of(exceptionCode.getStatus(), exceptionCode.getMessage());
            exchange.getResponse().setStatusCode(exceptionCode.getStatus());
        } else if (throwable instanceof ResponseStatusException) {
            ResponseStatusException ex = (ResponseStatusException) throwable;
            HttpStatus status = (HttpStatus) ex.getStatusCode(); // 정확히 캐스팅
            errorResponse = ErrorResponse.of(status, ex.getMessage());
            exchange.getResponse().setStatusCode(status);
        } else {
            errorResponse = ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage());
            exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            DataBuffer dataBuffer = bufferFactory.wrap(objectMapper.writeValueAsBytes(errorResponse));
            return exchange.getResponse().writeWith(Mono.just(dataBuffer));
        } catch (JsonProcessingException e) {
            DataBuffer dataBuffer = bufferFactory.wrap("{}".getBytes());
            return exchange.getResponse().writeWith(Mono.just(dataBuffer));
        }
    }
}
