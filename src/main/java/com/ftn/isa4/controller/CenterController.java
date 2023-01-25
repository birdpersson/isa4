package com.ftn.isa4.controller;

import com.ftn.isa4.dto.AppointmentRequest;
import com.ftn.isa4.dto.AppointmentResponse;
import com.ftn.isa4.dto.CenterResponse;
import com.ftn.isa4.model.Appointment;
import com.ftn.isa4.model.Center;
import com.ftn.isa4.service.AppointmentService;
import com.ftn.isa4.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.threeten.extra.Interval;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/center")
public class CenterController {
    @Autowired
    private CenterService centerService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/")
    public ResponseEntity<Collection<CenterResponse>> getCenters() {
        Collection<Center> centers = centerService.findAll();
        Collection<CenterResponse> response = new ArrayList<>();
        for (Center c : centers) {
            response.add(new CenterResponse(c));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CenterResponse> getCenter(@PathVariable String id) {
        return new ResponseEntity<>(new CenterResponse(centerService.findById(Long.parseLong(id))), HttpStatus.OK);
    }

    @PostMapping("/{id}/appointment")
    public ResponseEntity<AppointmentResponse> createAppointment(@PathVariable String id, @RequestBody AppointmentRequest dto) {
        Center center = centerService.findById(Long.parseLong(id));
        Interval interval = Interval.of(dto.getStart(), dto.getEnd());
        System.out.println("Received:" + interval);

        if (!center.isWithinWorkHours(interval))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        System.out.println("Creating:" + interval);

        for (Appointment a : center.getAppointments()) {
            System.out.println("Interval:" + a.getInterval());
            if (interval.overlaps(a.getInterval()))
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println("Created:" + interval);

        Appointment appointment = appointmentService.create(center, dto);
        return new ResponseEntity<>(new AppointmentResponse(appointment), HttpStatus.CREATED);
    }

}
