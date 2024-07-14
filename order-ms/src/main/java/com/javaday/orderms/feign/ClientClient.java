package com.javaday.orderms.feign;

import com.javaday.orderms.domain.dto.ClientDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClientClient {
    @Value("${app.client.url:http://localhost:8081/client/}")
    private String clientUrl;

    private RestTemplate restTemplate;

    public ClientClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ClientDto getClientById(final Long clientId) {
        return restTemplate.getForObject(clientUrl + clientId, ClientDto.class);
    }
}
