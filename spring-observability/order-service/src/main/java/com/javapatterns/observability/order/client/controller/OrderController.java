package com.javapatterns.observability.order.client.controller;

import com.javapatterns.observability.order.client.ProductCatalogClient;
import com.javapatterns.observability.order.client.domain.OrderRequest;
import com.javapatterns.observability.order.client.domain.Product;
import com.javapatterns.observability.order.client.domain.entity.Order;
import com.javapatterns.observability.order.client.domain.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository orderRepository;
    private final ProductCatalogClient productCatalogClient;

    public OrderController(OrderRepository orderRepository, ProductCatalogClient productCatalogClient) {
        this.orderRepository = orderRepository;
        this.productCatalogClient = productCatalogClient;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        Product product = productCatalogClient.getProductById(orderRequest.getProductId());
        Order order = new Order();
        order.setProductId(product.getId());
        order.setQuantity(orderRequest.getQuantity());
        order.setTotalPrice(product.getPrice() * orderRequest.getQuantity());
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @GetMapping
    public ResponseEntity<Iterable<Order>> getOrders() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }
    // Add additional endpoints to retrieve, update, and delete orders
}