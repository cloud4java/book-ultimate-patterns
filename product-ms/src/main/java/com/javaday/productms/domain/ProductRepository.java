package com.javaday.productms.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<com.javaday.productms.domain.Product, Long> {
}