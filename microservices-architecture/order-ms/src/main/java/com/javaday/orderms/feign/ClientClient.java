package com.javaday.orderms.feign;

import com.javaday.orderms.domain.dto.ClientDto;
import io.micrometer.observation.annotation.Observed;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClientClient {
    @Value("${app.client.url}")
    private String clientUrl;

    private RestTemplate restTemplate;

    public ClientClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Observed(name = "getClientById")
    public ClientDto getClientById(final Long clientId) {
        return restTemplate.getForObject(clientUrl + clientId, ClientDto.class);
    }
}
