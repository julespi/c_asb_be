package com.julespi.springbootapirest.models.services;

import com.julespi.springbootapirest.models.dao.IClientDao;
import com.julespi.springbootapirest.models.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    @Transactional(readOnly = true)
    public Page<Client> findAll(Pageable pageable) {
        return iClientDao.findAll(pageable);
    }

    @Override
    @Transactional
    public Client save(Client client) {
        return iClientDao.save(client);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        iClientDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Client findById(Long id) {
        return iClientDao.findById(id).orElse(null);
    }
}
