package com.system.stayRent.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Component
public class LoggingFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        Instant startTime = Instant.now();

        String method = request.getMethod() != null
                ? request.getMethod().name()
                : "UNKNOWN";

        String path = request.getURI().getPath();

        log.info("➡️  Incoming Request: {} {}", method, path);

        return chain.filter(exchange)
                .doOnTerminate(() -> {
                    Instant endTime = Instant.now();
                    long durationMs = Duration.between(startTime, endTime).toMillis();

                    int status = response.getStatusCode() != null
                            ? response.getStatusCode().value()
                            : 0;

                    log.info("⬅️  Response: {} {} - {} ({} ms)",
                            method, path, status, durationMs);
                });
    }
}

