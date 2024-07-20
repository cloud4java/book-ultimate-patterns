package com.javaday.orderms.domain.dto;

public class OrderRequest {
    private Long clientId;
    private Long productId;
    private Double productPrice;

    public OrderRequest() {
    }

    public OrderRequest(final Long clientId, final Long productId, final Double productPrice) {
        this.clientId = clientId;
        this.productId = productId;
        this.productPrice = productPrice;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(final Long clientId) {
        this.clientId = clientId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(final Long productId) {
        this.productId = productId;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(final Double productPrice) {
        this.productPrice = productPrice;
    }
}