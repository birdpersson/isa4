package com.ftn.isa4.service;

import com.ftn.isa4.model.Center;
import com.ftn.isa4.repository.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CenterService {
    @Autowired
    private CenterRepository repository;

    public Center findById(Long id) {
        return repository.findById(id).orElseGet(null);
    }

    public Collection<Center> findAll() {
        return repository.findAll();
    }

}
