package com.silva.bookstore.controller;

import com.silva.bookstore.domain.Book;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @RateLimiter(name = "bookStoreLimiter")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getAllBooks() {
        return Arrays.asList(new Book());
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return new Book();

    }
}