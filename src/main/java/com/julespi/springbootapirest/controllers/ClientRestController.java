package com.julespi.springbootapirest.controllers;

import com.julespi.springbootapirest.models.entity.Client;
import com.julespi.springbootapirest.models.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClientRestController {

    @Autowired
    private IClientService clientService;

    @GetMapping("/clients")
    public List<Client> index(){
        return clientService.findAll();
    }

    @GetMapping("/clients/{id}")
    public Client show(@PathVariable Long id){
        return clientService.findById(id);
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client client){
        return clientService.save(client);
    }

    @PutMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client update(@RequestBody Client client, @PathVariable Long id){
        Client actualClient = clientService.findById(id);
        actualClient.setName(client.getName());
        actualClient.setLastName(client.getLastName());
        actualClient.setEmail(client.getEmail());
        return clientService.save(actualClient);
    }

    @DeleteMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        clientService.delete(id);
    }
}
