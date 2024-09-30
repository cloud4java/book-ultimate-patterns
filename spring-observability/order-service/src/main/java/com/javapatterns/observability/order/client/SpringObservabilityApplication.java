package com.javapatterns.observability.order.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringObservabilityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringObservabilityApplication.class, args);
	}

}
