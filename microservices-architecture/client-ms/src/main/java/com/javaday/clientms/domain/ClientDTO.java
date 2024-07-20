package com.javaday.clientms.domain;

public class ClientDTO {
    private String name;
    private String sureName;
    private String email;

    public ClientDTO(String name, String sureName, String email) {
        this.name = name;
        this.sureName = sureName;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSureName() {
        return sureName;
    }

    public String getEmail() {
        return email;
    }
}
