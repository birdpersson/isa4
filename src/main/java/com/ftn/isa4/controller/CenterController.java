package com.ftn.isa4.controller;

import com.ftn.isa4.dto.AppointmentRequest;
import com.ftn.isa4.dto.AppointmentResponse;
import com.ftn.isa4.dto.CenterResponse;
import com.ftn.isa4.dto.ReservationResponse;
import com.ftn.isa4.model.Appointment;
import com.ftn.isa4.model.Center;
import com.ftn.isa4.model.User;
import com.ftn.isa4.security.TokenUtils;
import com.ftn.isa4.service.AppointmentService;
import com.ftn.isa4.service.CenterService;
import com.ftn.isa4.service.EmailService;
import com.ftn.isa4.service.UserService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.threeten.extra.Interval;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/center")
public class CenterController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

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
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<AppointmentResponse> createAppointment(@PathVariable String id, @RequestBody AppointmentRequest dto) {
        Center center = centerService.findById(Long.parseLong(id));
        Interval interval = Interval.of(dto.getStart(), dto.getEnd());
        System.out.println("Received:" + interval);

        if (!center.isWithinWorkHours(interval))
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);

        System.out.println("Creating:" + interval);

        for (Appointment a : center.getAppointments()) {
            System.out.println("Interval:" + a.getInterval());
            if (!a.isCanceled() && interval.overlaps(a.getInterval()))
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        System.out.println("Created:" + interval);

        Appointment appointment = appointmentService.create(center, dto);
        return new ResponseEntity<>(new AppointmentResponse(appointment), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/reservation")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ReservationResponse> createReservation(@PathVariable String id, @RequestBody AppointmentRequest dto, HttpServletRequest request) {
        User user = userService.findByUsername(tokenUtils.getUsernameFromToken(tokenUtils.getToken(request)));
        Center center = centerService.findById(Long.parseLong(id));

        if (user.getQuestionnaire().isEmpty() || user.getQuestionnaire().contains("O0"))
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);

        Interval interval = Interval.of(dto.getStart(), dto.getEnd());
        System.out.println("Received:" + interval);

        if (!center.isWithinWorkHours(interval))
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);

        System.out.println("Creating:" + interval);

        for (Appointment a : center.getAppointments()) {
            System.out.println("Interval:" + a.getInterval());
            if (!a.isCanceled() && interval.overlaps(a.getInterval()))
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            if ((a.isCanceled() && interval.overlaps(a.getInterval())) && user.getId().equals(a.getPatient().getId()))
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Appointment appointment = appointmentService.create(center, dto);
        Appointment reservation = appointmentService.reserve(user, appointment);
        try {
            emailService.sendReservationMail(user, reservation);
        } catch (MailException | IOException | WriterException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ReservationResponse(reservation), HttpStatus.CREATED);
    }
}
