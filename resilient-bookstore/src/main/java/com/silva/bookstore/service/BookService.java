package com.silva.bookstore.service;

import com.silva.bookstore.domain.Book;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class BookService {
    private Logger LOG = org.slf4j.LoggerFactory.getLogger(BookService.class);
    public List<Book> getAllBooks() {
        return Arrays.asList(new Book("Book Author 1", 1L, 127.99, "Book Title"));
    }

    public Book getBookById(Long id) {
        LOG.info("Getting book by ID");
        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR Getting book by ID");
//        return new Book("Book Author 1", 1L, 127.99, "Book Title");
    }

    public Book createBook(Book book) {
        return book;
    }

    public CompletableFuture<Book> updateBook(final Long id, final Book book) {
      return   CompletableFuture.runAsync(() -> {
            LOG.info("Updating book...");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                //sleep skipped
            }
        }).thenApply( (result) -> new Book("Book Author 1", 1L, 127.99, "Book Title")) ;
    }
}