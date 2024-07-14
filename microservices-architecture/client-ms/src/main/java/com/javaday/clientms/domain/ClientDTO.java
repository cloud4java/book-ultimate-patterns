package com.javaday.clientms.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
}