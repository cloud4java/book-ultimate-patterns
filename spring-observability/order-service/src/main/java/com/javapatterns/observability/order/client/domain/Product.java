package com.javapatterns.observability.order.client.domain;
//import jakarta.persistence.*;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    // Getters, setters, and constructors
}
