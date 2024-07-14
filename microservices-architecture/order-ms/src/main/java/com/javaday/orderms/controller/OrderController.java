package com.javaday.orderms.controller;

import com.javaday.orderms.domain.Order;
import com.javaday.orderms.domain.dto.OrderRequest;
import com.javaday.orderms.domain.dto.OrderResponse;
import com.javaday.orderms.domain.dto.ProductDto;
import com.javaday.orderms.feign.ProductClient;
import com.javaday.orderms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductClient productClient;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> create1(@RequestBody OrderRequest orderRequest) {
        OrderResponse response = orderService.save(orderRequest);
        return ResponseEntity.ok(response);
    }

/*    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest orderRequest){
        Order orderEntity = new Order();
        orderEntity.setClientId(orderRequest.getClientId());
        orderEntity.setProductId(orderRequest.getProductId());
        Order order = repository.save(orderEntity);
        return  ResponseEntity.ok(this.retrieveById(order.getId()));
    }*/

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> findById(@PathVariable Long id) {
        OrderResponse orderResponse = orderService.getById(id);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping
    private List<Order> getAll() {
        return orderService.getAll();
    }

    private OrderResponse retrieveById(final Long id) {

        ProductDto productDto = productClient.findById(id);

        return new OrderResponse(id, "Fix Client Name", productDto.getName(), productDto.getPrice());
    }

}