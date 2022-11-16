package com.ftn.isa4.service;

import com.ftn.isa4.model.Appointment;
import com.ftn.isa4.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository repository;

    public Appointment findById(Long id) {
        return repository.findById(id).orElseGet(null);
    }

}
