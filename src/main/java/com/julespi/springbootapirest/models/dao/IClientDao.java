package com.julespi.springbootapirest.models.dao;

import com.julespi.springbootapirest.models.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface IClientDao extends CrudRepository<Client, Long> {
}
