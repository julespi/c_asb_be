package com.julespi.springbootapirest.models.dao;

import com.julespi.springbootapirest.models.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientDao extends JpaRepository<Client, Long> {
}
