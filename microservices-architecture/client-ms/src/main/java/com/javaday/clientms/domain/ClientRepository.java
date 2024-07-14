package com.javaday.clientms.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<com.javaday.clientms.domain.Client, Long> {
}