package com.javaday.clientms.controller;

import com.javaday.clientms.domain.Client;
import com.javaday.clientms.service.ClientService;
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
    @Autowired
    private ClientService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> findById(@PathVariable Long id){
        Optional<Client> cli = service.getById(id);
        if (cli.isPresent()) {
            return new ResponseEntity(cli,HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> findAll(){
        return service.getAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Client save(@RequestBody Client client){
        return service.save(client);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client){
        Optional<Client> cli = service.getById(id);
        if (cli.isPresent()) {
            Client c = cli.get();
            c.setAge(client.getAge());
            c.setName(client.getName());
            return new ResponseEntity(service. save(c),HttpStatus.OK);
        }else{
          return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
