package com.javaday.orderms.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<com.javaday.orderms.domain.Order, Long> {
}
