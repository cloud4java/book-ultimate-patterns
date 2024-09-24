package com.silva.bookstore.exception;

import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.concurrent.TimeoutException;

@RestControllerAdvice
public class BookStoreExceptionHandler extends ResponseEntityExceptionHandler {
    private Logger LOG = LoggerFactory.getLogger(BookStoreExceptionHandler.class);
    @ExceptionHandler(RequestNotPermitted.class)
    public ResponseEntity<String>handleRequestNotPermitted(RequestNotPermitted e) {
        LOG.error("Too many requests", e);
        return ResponseEntity.status(429).body("Too many requests");
    }

    @ExceptionHandler(BulkheadFullException.class)
    public ResponseEntity<String> handleBulkheadFullException(BulkheadFullException e) {
        LOG.error("Bulkhead is full", e);
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Bulkhead is full");
    }

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<String> handleTimeoutException(TimeoutException e) {
        LOG.error("Request timed out", e);
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timed out");
    }

    @ExceptionHandler(CallNotPermittedException.class)
    public ResponseEntity<String> handleCallNotPermitted(CallNotPermittedException ex){
        LOG.error("Circuit breaker is open", ex);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Circuit breaker is open");
    }

}
