package com.ftn.isa4.service;

import com.ftn.isa4.model.Appointment;
import com.ftn.isa4.model.User;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

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
    public void sendReservationMail(User user, Appointment appointment) throws IOException, WriterException {
        String text = "http://localhost:8080/appointment/" + appointment.getId();
        String path = generateQRCodeImage(text);
        MimeMessagePreparator preparator = mimeMessage -> {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getUsername()));
            mimeMessage.setFrom(env.getProperty("spring.mail.username"));
            mimeMessage.setSubject("Reservation confirmed ISA12");

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setText("<html><body><img src='cid:id1'></body></html>", true);

            FileSystemResource res = new FileSystemResource(new File(path));
            helper.addInline("id1", res);
        };

        try {
            javaMailSender.send(preparator);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private static String generateQRCodeImage(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 400, 400);

        String fileName = UUID.randomUUID() + ".png";
        String home = System.getProperty("user.home");
        String path = home + File.separator + "isa" + File.separator + "images";

        Path uploadPath = Paths.get(path);
        if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);
        Path filePath = uploadPath.resolve(fileName);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", filePath);
        return filePath.toString();
    }
}
