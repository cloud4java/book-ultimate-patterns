package com.javaday.orderms.feign;

import com.javaday.orderms.domain.dto.ProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductClient {
    @Value("${app.product.url}")
    private String productUrl;
    private RestTemplate  restTemplate;

    public ProductClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public ProductDto findById(Long id) {
        return restTemplate.getForObject(productUrl + id, ProductDto.class);
    }
}
