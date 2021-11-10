package com.julespi.springbootapirest.models.services;

import com.julespi.springbootapirest.models.entity.Client;

import java.util.List;

public interface IClientService {

    public List<Client> findAll();
}
