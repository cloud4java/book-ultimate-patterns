package com.javaday.orderms.domain.dto;

public class ClientDto {
    private long id;
    private String name;
    private int age;

    public ClientDto() {
    }

    public ClientDto(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}