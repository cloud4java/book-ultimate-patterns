package com.javaday.orderms.service;

import com.javaday.orderms.domain.Order;
import com.javaday.orderms.domain.OrderRepository;
import com.javaday.orderms.domain.dto.ClientDto;
import com.javaday.orderms.domain.dto.OrderRequest;
import com.javaday.orderms.domain.dto.OrderResponse;
import com.javaday.orderms.domain.dto.ProductDto;
import com.javaday.orderms.feign.ClientClient;
import com.javaday.orderms.feign.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductClient  productClient;
    @Autowired
    private ClientClient clientClient;

    public List<Order> getAll() {
        return (List<Order>) repository.findAll();
    }

    public OrderResponse getById(Long id) {

        Order order = repository.findById(id).orElseThrow();
        ClientDto clientDto = clientClient.getClientById(order.getClientId());
        ProductDto productDto = productClient.findById(order.getProductId());

        return new OrderResponse( clientDto.getName(),id, productDto.getName(), productDto.getPrice());
    }

    public OrderResponse save(OrderRequest orderRequest) {
        Order orderEntity = new Order();
        orderEntity.setClientId(orderRequest.getClientId());
        orderEntity.setProductId(orderRequest.getProductId());
        Order order = repository.save(orderEntity);
        return getById(order.getId());
    }
}
