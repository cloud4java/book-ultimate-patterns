package com.javaday.productms.controller;

import com.javaday.productms.domain.Product;
import com.javaday.productms.domain.ProductRepository;
import com.javaday.productms.service.ProductService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService service;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        LOGGER.info("Searching product by id: {} ", id);
        Optional<Product> prod = productRepository.findById(id);
        if (prod.isPresent()) {
            return new ResponseEntity(prod, HttpStatus.OK);
        } else {
            LOGGER.warn("Product not found by id {} ", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> findAll() {
        LOGGER.info("Getting all products");

        return service.getAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product save(@RequestBody Product product) {
        LOGGER.info("Creating product");
        return productRepository.save(product);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        LOGGER.info("Updating product by id {} ", id);
        Optional<Product> prod = service.getById(id);
        if (prod.isPresent()) {
            Product p = prod.get();
            p.setName(product.getName());
            p.setName(product.getName());
            LOGGER.info("Updating client to : {} id {}", p, id);
            Product saved = service.save(p);
            return new ResponseEntity(saved, HttpStatus.OK);
        } else {
            LOGGER.warn("Product not found by id {} ", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}