package com.system.stayRent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;

@Component
public class DefaultProblemDetailFactoryImpl implements ProblemDetailFactory {

    @Override
    public ProblemDetail create(HttpStatus status, String message, ServerWebExchange exchange) {
        return create(status, message, status.name(), exchange);
    }

    @Override
    public ProblemDetail create(HttpStatus status, String message, ServerWebExchange exchange, Map<String, Object> properties) {
        ProblemDetail pd = create(status, message, exchange);
        if (Objects.nonNull(properties)) {
            properties.forEach(pd::setProperty);
        }
        return pd;
    }

    @Override
    public ProblemDetail create(HttpStatus status, String message, String errorCode, ServerWebExchange exchange) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(status, message);
        detail.setTitle(status.getReasonPhrase());
        detail.setProperty("timestamp", Instant.now());
        detail.setProperty("Path", exchange.getRequest().getURI().getPath());
        detail.setProperty("errorCode", errorCode);
        detail.setProperty("service", "room-service");
        return detail;
    }


}
