package com.ftn.isa4.service;

import com.ftn.isa4.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Async
    public void sendRegistrationMail(User user) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getUsername());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Confirm registration to ISA12");
        mail.setText("http://localhost:8080/auth/verify?token=" + user.getToken());
        javaMailSender.send(mail);
    }

    @Async
    public void sendReservationMail(User user) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getUsername());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Reservation confirmed ISA12");
        mail.setText("http://localhost:4200/user/" + user.getUsername());
        javaMailSender.send(mail);
    }
}
