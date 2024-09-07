package com.silva.bookstore.controller;

import com.silva.bookstore.domain.Book;
import com.silva.bookstore.service.BookService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @RateLimiter(name = "bookStoreLimiter")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getAllBooks() {
        return Arrays.asList(new Book("Book Author 1", 1L, 127.99, "Book Title"));
    }

    @PostMapping
    @Bulkhead(name = "bookstoreBulkhead")
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(new Book("Book Author 1", 1L, 127.99, "Book Title"));
    }
}
