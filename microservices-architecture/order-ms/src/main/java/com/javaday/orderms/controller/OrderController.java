package com.javaday.orderms.controller;

import com.javaday.orderms.domain.Order;
import com.javaday.orderms.domain.dto.OrderRequest;
import com.javaday.orderms.domain.dto.OrderResponse;
import com.javaday.orderms.feign.ProductClient;
import com.javaday.orderms.service.OrderService;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductClient productClient;

    @Observed(name = "place-order")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> create1(@RequestBody OrderRequest orderRequest) {
        LOGGER.info("Creating order {}", orderRequest);
        OrderResponse response = orderService.save(orderRequest);
        return ResponseEntity.ok(response);
    }

    @Observed(name = "get-order-byId")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> findById(@PathVariable Long id) {
        LOGGER.info("Getting order by id: {} ", id);

        OrderResponse orderResponse = orderService.getById(id);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping
    private List<Order> getAll() {
        LOGGER.info("Getting all orders");

        return orderService.getAll();
    }

}