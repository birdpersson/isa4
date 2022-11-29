package com.ftn.isa4.service;

import com.ftn.isa4.dto.AppointmentRequest;
import com.ftn.isa4.model.Appointment;
import com.ftn.isa4.model.Center;
import com.ftn.isa4.model.User;
import com.ftn.isa4.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.threeten.extra.Interval;

import java.time.Duration;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository repository;

    public Appointment findById(Long id) {
        return repository.findById(id).orElseGet(null);
    }

    public Appointment create(Center center, AppointmentRequest dto) {
        Appointment a = new Appointment();
        a.setType(Appointment.Type.AVAILABLE);
        a.setInterval(Interval.of(dto.getStart(), Duration.ofMinutes(dto.getDuration())));
        a.setCenter(center);
        center.addAppointment(a);
        return repository.save(a);
    }

    public Appointment reserve(User patient, Appointment appointment) {
        appointment.setType(Appointment.Type.RESERVED);
        appointment.setPatient(patient);
        patient.addAppointment(appointment);
        return repository.save(appointment);
    }

    public Appointment cancel(Appointment appointment) {
        appointment.setType(Appointment.Type.CANCELED);
        return repository.save(appointment);
    }

}
