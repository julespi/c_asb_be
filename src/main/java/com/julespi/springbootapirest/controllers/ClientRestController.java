package com.julespi.springbootapirest.controllers;

import com.julespi.springbootapirest.models.entity.Client;
import com.julespi.springbootapirest.models.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClientRestController {

    @Autowired
    private IClientService clientService;

    @GetMapping("/clients")
    public ResponseEntity<?> index() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Exist");
        response.put("payload", clientService.findAll());
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/clients/page/{page}")
    public ResponseEntity<?> index(@PathVariable Integer page) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Exist");
        response.put("payload", clientService.findAll(PageRequest.of(page,4)));
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Client client = null;
        Map<String, Object> response = new HashMap<>();
        try {
            client = clientService.findById(id);
        }catch (DataAccessException e){
            response.put("message", "Error while querying database.");
            response.put("payload",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (client == null) {
            response.put("message", "Client with id: ".concat(id.toString()).concat(" does not exist."));
            response.put("payload","");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        response.put("message", "Exist");
        response.put("payload", client);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PostMapping("/clients")
    public ResponseEntity<?> create(@Valid @RequestBody Client client, BindingResult result) {
        Client newClient = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){

            // como lista de strings
            /*List<String> errors = new ArrayList<>();
            for(FieldError err: result.getFieldErrors()){
                errors.add("The field '"+err.getField()+"' "+err.getDefaultMessage());
            }*/
            //como stream
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "The field '"+err.getField()+"' "+err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("message", "Error while validating.");
            response.put("payload",errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            newClient = clientService.save(client);
        }catch (DataAccessException e){
            response.put("message", "Error while inserting in database.");
            response.put("payload",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Client has successfully been created");
        response.put("payload", newClient);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Client client, BindingResult result, @PathVariable Long id) {
        Client actualClient = clientService.findById(id);
        Client updatedClient = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){

            // como lista de strings
            /*List<String> errors = new ArrayList<>();
            for(FieldError err: result.getFieldErrors()){
                errors.add("The field '"+err.getField()+"' "+err.getDefaultMessage());
            }*/
            //como stream
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "The field '"+err.getField()+"' "+err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("message", "Error while validating.");
            response.put("payload",errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (actualClient == null) {
            response.put("message", "Could not edit. Client with id: ".concat(id.toString()).concat(" does not exist."));
            response.put("payload", "");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            actualClient.setName(client.getName());
            actualClient.setLast_name(client.getLast_name());
            actualClient.setEmail(client.getEmail());
            actualClient.setCreated(client.getCreated());

            updatedClient = clientService.save(actualClient);
        }catch (DataAccessException e){
            response.put("message", "Error while updating client in database.");
            response.put("payload",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Client has successfully been updated");
        response.put("payload", updatedClient);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            clientService.delete(id);
        }catch (DataAccessException e){
            response.put("message", "Error while deleting client in database.");
            response.put("payload",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Client has successfully been deleted");
        response.put("payload", "");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
    }
}
