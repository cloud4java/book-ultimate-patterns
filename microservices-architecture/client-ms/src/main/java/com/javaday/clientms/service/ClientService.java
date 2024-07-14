package com.javaday.clientms.service;

import com.javaday.clientms.domain.Client;
import com.javaday.clientms.domain.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public List<Client> getAll() {
        return (List<Client>) repository.findAll();
    }
    public Optional<Client> getById(Long id) {
        return repository.findById(id);
    }

    public Client save(Client client) {
        Client savedClient = repository.save(client);
//        this.sendClientToKafka(savedClient);
        return savedClient;
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendClientToKafka(Client client) {
        String clientJson = client.toJson();
        kafkaTemplate.send("client-topic", clientJson);
    }

}