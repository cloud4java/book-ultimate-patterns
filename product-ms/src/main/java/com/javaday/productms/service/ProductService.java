package com.javaday.productms.service;

import com.javaday.productms.domain.Product;
import com.javaday.productms.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getAll(){
      return (List<Product>) repository.findAll();
    }
    public Optional<Product> getById(Long id){
        return repository.findById(id);
    }
    public Product save(Product product){
        return repository.save(product);
    }
}