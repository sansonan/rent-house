package com.system.stayRent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.server.ServerWebExchange;

import java.util.Map;

public interface ProblemDetailFactory {
    ProblemDetail create(HttpStatus status, String message, ServerWebExchange exchange);
    ProblemDetail create(HttpStatus status, String message, ServerWebExchange exchange, Map<String, Object> properties);
    ProblemDetail create(HttpStatus status, String message, String errorCode, ServerWebExchange exchange);
}
