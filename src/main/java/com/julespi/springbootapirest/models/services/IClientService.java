package com.julespi.springbootapirest.models.services;

import com.julespi.springbootapirest.models.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClientService {

    public List<Client> findAll();
    public Page<Client> findAll(Pageable pageable);
    public Client save(Client client);
    public void delete(Long id);
    public Client findById(Long id);
}
