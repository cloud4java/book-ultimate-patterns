package com.javapatterns.observability.order.client.domain;

import lombok.Data;

@Data
public class OrderRequest {
    private Long productId;
    private int quantity;
    private Double totalPrice;
}
