package com.javapatterns.observability.order.client.domain.repository;

import com.javapatterns.observability.order.client.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
