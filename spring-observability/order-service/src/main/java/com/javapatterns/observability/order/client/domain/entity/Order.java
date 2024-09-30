package com.javapatterns.observability.order.client.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private int quantity;
    private Double totalPrice;
    // Getters, setters, and constructors
}
