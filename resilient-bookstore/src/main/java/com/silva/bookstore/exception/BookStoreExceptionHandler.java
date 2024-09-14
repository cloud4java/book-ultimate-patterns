package com.silva.bookstore.exception;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class BookStoreExceptionHandler extends ResponseEntityExceptionHandler {
    private Logger LOG = LoggerFactory.getLogger(BookStoreExceptionHandler.class);
    @ExceptionHandler(RequestNotPermitted.class)
    public ResponseEntity<String>handleRequestNotPermitted(RequestNotPermitted e) {
        LOG.error("Too many requests", e);
        return ResponseEntity.status(429).body("Too many requests");
    }

//    @ExceptionHandler(BulkheadFullException.class)
//    public ResponseEntity<String> handleBulkheadFullException(BulkheadFullException e) {
//        LOG.error("Bulkhead is full", e);
//        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Bulkhead is full");
//    }
}
