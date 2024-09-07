package com.silva.bookstore.exception;

import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class BookStoreExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RequestNotPermitted.class)
    public ResponseEntity<String>handleRequestNotPermitted(RequestNotPermitted e) {
        return ResponseEntity.status(429).body("Too many requests");
    }

    @ExceptionHandler(BulkheadFullException.class)
    public ResponseEntity<String> handleBulkheadFullException(BulkheadFullException e) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Bulkhead is full");
    }
}
