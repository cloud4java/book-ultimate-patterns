package com.silva.bookstore.domain;

public class Book {
    private Long id;
    private String title;
    private String author;
    private double price;

    public Book(final String author, final Long id, final double price, final String title) {
        this.author = author;
        this.id = id;
        this.price = price;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public Long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }
}