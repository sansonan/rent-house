package com.system.stayRent.exception;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Hidden
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ProblemDetailFactory pf;

//    @ExceptionHandler(WebExchangeBindException.class)
//    public Mono<ProblemDetail> handleConstraintViolation(Exception ex, ServerWebExchange exchange) {
//        log.error("Type of exception:{} ", ex.getClass().getName());
//        log.warn("Constraint violation: {} ", ex.getMessage());
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
//        return Mono.just(problemDetail);
//    }
//
//
//    @ExceptionHandler(RoomNotFoundException.class)
//    public Mono<ProblemDetail> handleRoomNotFound(RoomNotFoundException ex, ServerWebExchange exchange) {
//        log.warn("Room not found: {} ", ex.getMessage());
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
//        return Mono.just(problemDetail);
//    }


   @ExceptionHandler(RoomNotFoundException.class)
   public Mono<ProblemDetail> handleRoomNotFound(RoomNotFoundException ex, ServerWebExchange exchange) {
            log.warn("Room Not Found: {} ", ex.getMessage());
            return Mono.just(pf.create(HttpStatus.NOT_FOUND, ex.getMessage(),ErrorCode.ROOM_NOT_FOUND.name(), exchange));
   }
   @ExceptionHandler(WebExchangeBindException.class)
   public Mono<ProblemDetail> handleWebExchangeBindException(WebExchangeBindException ex, ServerWebExchange exchange){
          log.warn("Constrain violation: {} ", ex.getMessage());
          return Mono.just(pf.create(HttpStatus.BAD_REQUEST, ex.getMessage(),ErrorCode.CONSTRAINT_VIOLATION.name() , exchange));
   }

   @ExceptionHandler(Exception.class)
   public Mono<ProblemDetail> handleException(Exception ex, ServerWebExchange exchange) {
          log.warn("Exception : {}", ex.getMessage());
          return Mono.just(pf.create(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),ErrorCode.SYSTEM_ERROR.name() , exchange));
   }

}
