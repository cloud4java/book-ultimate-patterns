package com.javapatterns.observability.order.client;

import com.javapatterns.observability.order.client.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-catalog", url = "${product-catalog.url}")
public interface ProductCatalogClient {
    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable("id") Long id);
}
