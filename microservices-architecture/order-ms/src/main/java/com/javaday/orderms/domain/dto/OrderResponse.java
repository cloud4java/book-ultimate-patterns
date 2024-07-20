package com.javaday.orderms.domain.dto;

public class OrderResponse {
    private Long orderId;
    private String clientName;
    private String productName;
   private Double productPrice;

    public OrderResponse() {
    }

    public OrderResponse(final String clientName, final Long orderId, final String productName, final Double productPrice) {
        this.clientName = clientName;
        this.orderId = orderId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(final String clientName) {
        this.clientName = clientName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(final Long orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(final Double productPrice) {
        this.productPrice = productPrice;
    }
}
