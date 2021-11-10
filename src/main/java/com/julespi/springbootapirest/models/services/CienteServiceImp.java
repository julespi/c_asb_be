package com.julespi.springbootapirest.models.services;

import com.julespi.springbootapirest.models.dao.IClientDao;
import com.julespi.springbootapirest.models.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class CienteServiceImp implements IClientService{

    @Autowired
    private IClientDao iClientDao;

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return (List<Client>) iClientDao.findAll();
    }
}
