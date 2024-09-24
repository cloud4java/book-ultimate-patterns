package com.silva.bookstore.controller;

import com.silva.bookstore.domain.Book;
import com.silva.bookstore.service.BookService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(BookController.class);
    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @RateLimiter(name = "bookStoreLimiter")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getAllBooks() {
        return List.of(new Book("Book Author 1", 1L, 127.99, "Book Title"));
    }

    @PostMapping
    @Bulkhead(name = "bookStoreBulkhead")
    public Book createBook(@RequestBody Book book) {
        final Book book1 = new Book("Book Author 1_" + System.currentTimeMillis(), 1L, 127.99, "Book Title");
        LOG.info("Creating book " + book1);
        return bookService.createBook(book1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PutMapping("/{id}")
    @TimeLimiter(name = "bookStoreTimeout")
    public CompletableFuture<Book> updateBookAsync(@PathVariable Long id, @RequestBody Book book) {

        return   CompletableFuture.runAsync(() -> {
            LOG.info("Delay updating book...");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                //sleep skipped
            }
        }).thenApply( (result) -> new Book("Book Author 1", 1L, 127.99, "Book Title")) ;
    }
    @GetMapping("/{id}/authors")
    @CircuitBreaker(name = "bookstoreBackend", fallbackMethod = "getAuthorsByBookById" )
    public ResponseEntity<Object> getAuthorsByBookById(@PathVariable Long id) {
        LOG.info("GERROR getting authors by  book by ID");
        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR Getting authors by  book by ID");
    }

    public ResponseEntity<Object> getAuthorsByBookById(Long id, Exception e) {
        return ResponseEntity.ok(new Book("BOOK AUTHOR FROM CACHE", 1L, 127.99, "BOOK TITLE FROM CACHE"));
    }
}
