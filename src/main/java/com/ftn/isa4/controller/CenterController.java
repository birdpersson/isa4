package com.ftn.isa4.controller;

import com.ftn.isa4.dto.AppointmentRequest;
import com.ftn.isa4.dto.AppointmentResponse;
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

import java.time.Duration;
import java.util.Collection;

@Controller
@RequestMapping("/center")
public class CenterController {
    @Autowired
    private CenterService centerService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/")
    public ResponseEntity<Collection<Center>> getCenters() {
        return new ResponseEntity<>(centerService.findAll(), HttpStatus.OK);
    }

    @PostMapping("{id}/appointment")
    public ResponseEntity<AppointmentResponse> createAppointment(@PathVariable String id, @RequestBody AppointmentRequest dto) {
        Center center = centerService.findById(Long.parseLong(id));
        Interval interval = Interval.of(dto.getStart(), Duration.ofMinutes(dto.getDuration()));

        //TODO: check if interval between work hours
        System.out.println("Creating:" + interval);

        for (Appointment a : center.getAppointments()) {
            System.out.println("Interval:" + a.getInterval());
            if (!a.isCanceled() && interval.overlaps(a.getInterval()))
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Appointment appointment = appointmentService.create(center, dto);
        return new ResponseEntity<>(new AppointmentResponse(appointment), HttpStatus.CREATED);
    }

}
