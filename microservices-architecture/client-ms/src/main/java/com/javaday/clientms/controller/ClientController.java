package com.javaday.clientms.controller;

import com.javaday.clientms.domain.Client;
import com.javaday.clientms.service.ClientService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> findById(@PathVariable Long id){
        LOGGER.info("Getting client by id: {} ", id);

        Optional<Client> cli = service.getById(id);
        if (cli.isPresent()) {
            LOGGER.info("Found client by id: {} ", cli);
            return new ResponseEntity(cli,HttpStatus.OK);
        }else{
            LOGGER.info("Client not found by id: {} ", id);
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> findAll(){
        LOGGER.info("Getting all clients");

        final List<Client> all = service.getAll();
        LOGGER.info("Clients found: {} ", all.size());
        return all;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Client save(@RequestBody Client client){
        LOGGER.info("Creating client {}", client);
        final Client saved = service.save(client);
        LOGGER.info("Client created: {} ", saved);
        return saved;

    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client){
        LOGGER.info("Updating client : {} id {}", client, id);
        Optional<Client> cli = service.getById(id);
        if (cli.isPresent()) {
            Client c = cli.get();
            c.setAge(client.getAge());
            c.setName(client.getName());
            LOGGER.info("Updating client to : {} id {}", c, id);
            return new ResponseEntity(service. save(c),HttpStatus.OK);
        }else{
            LOGGER.info("Client Not Found by id: {} ", id);
          return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
