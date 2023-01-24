package com.ftn.isa4.controller;

import com.ftn.isa4.dto.AppointmentResponse;
import com.ftn.isa4.dto.ReservationResponse;
import com.ftn.isa4.model.Appointment;
import com.ftn.isa4.model.User;
import com.ftn.isa4.security.TokenUtils;
import com.ftn.isa4.service.AppointmentService;
import com.ftn.isa4.service.EmailService;
import com.ftn.isa4.service.UserService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/")
    private ResponseEntity<List<AppointmentResponse>> getAppointments(HttpServletRequest request) {
        User user = userService.findByUsername(tokenUtils.getUsernameFromToken(tokenUtils.getToken(request)));
        Collection<Appointment> appointments = user.getAppointments();
        List<AppointmentResponse> list = new ArrayList<>();
        for (Appointment a : appointments) {
            list.add(new AppointmentResponse(a));
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/{id}/reserve")
    public ResponseEntity<ReservationResponse> reserveAppointment(@PathVariable String id, HttpServletRequest request) {
        User user = userService.findByUsername(tokenUtils.getUsernameFromToken(tokenUtils.getToken(request)));
        Appointment appointment = appointmentService.findById(Long.parseLong(id));
        if (appointment.isReserved())
            return new ResponseEntity<>(HttpStatus.GONE);
        if (appointment.isCanceled() && user.getId().equals(appointment.getPatient().getId()))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        if (user.getQuestionnaire().isEmpty() || user.getQuestionnaire().contains("O0"))
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Appointment reservation = appointmentService.reserve(user, appointment);
        try {
            emailService.sendReservationMail(user, reservation);
        } catch (MailException | IOException | WriterException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ReservationResponse(reservation), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<AppointmentResponse> cancelAppointment(@PathVariable String id, HttpServletRequest request) {
        String username = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        Appointment appointment = appointmentService.findById(Long.parseLong(id));
        if (!appointment.getPatient().getUsername().equals(username))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        if (!appointment.is24hBefore())
            return new ResponseEntity<>(HttpStatus.GONE);
        return new ResponseEntity<>(new AppointmentResponse(appointmentService.cancel(appointment)), HttpStatus.CREATED);
    }

}
