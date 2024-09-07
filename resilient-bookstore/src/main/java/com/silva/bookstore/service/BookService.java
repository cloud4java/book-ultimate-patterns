package com.silva.bookstore.service;

import com.silva.bookstore.domain.Book;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    public List<Book> getAllBooks() {
        return Arrays.asList(new Book("Book Author 1", 1L, 127.99, "Book Title"));
    }

    public Book getBookById(Long id) {
        return new Book("Book Author 1", 1L, 127.99, "Book Title");
    }

    public Book createBook(Book book) {
        return book;
    }
}