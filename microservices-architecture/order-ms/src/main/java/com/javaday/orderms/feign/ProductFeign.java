//package com.javaday.orderms.feign;
//
//import com.javaday.orderms.domain.dto.ProductDto;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@FeignClient(name = "product-feign",url = "http://localhost:8082")
//
//public interface ProductFeign {
//
//    @RequestMapping( value = "/products/{id}", produces = "application/json")
//    ProductDto findById(Long id);
//
//
//    @RequestMapping(value = "products", produces = "application/json")
//    ProductDto getProduct();
//
//    @PostMapping
//    ProductDto create(ProductDto productDto);
//}
