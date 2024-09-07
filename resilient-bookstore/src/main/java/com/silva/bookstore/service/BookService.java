package com.silva.bookstore.service;

import com.silva.bookstore.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class BookService {
    public List<Book> getAllBooks() {
        return Arrays.asList(new Book());
    }
}