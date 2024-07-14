//package com.javaday.orderms.feign;
//
//import com.javaday.orderms.domain.dto.ClientDto;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@FeignClient(name = "client-feign",url = "http://localhost:8081")
//public interface ClientFeign {
//    @RequestMapping("/client")
//    ClientDto findById(Long idClient);
//
//    @PostMapping
//    ClientDto create(ClientDto clientDto);
//}
