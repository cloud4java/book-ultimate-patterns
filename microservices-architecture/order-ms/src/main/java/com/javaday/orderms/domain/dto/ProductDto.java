package com.javaday.orderms.domain.dto;

public class ProductDto{
    private long id;
    private Double price;
    private String name;

    public ProductDto() {
    }

    public ProductDto(final long id, final String name, final Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }
}