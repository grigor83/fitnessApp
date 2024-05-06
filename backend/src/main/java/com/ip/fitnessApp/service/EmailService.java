package com.ip.fitnessApp.service;

import com.ip.fitnessApp.model.Korisnik;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmailService {

    @Autowired
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendVerificationEmail(Korisnik user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getMejl());
        mailMessage.setSubject("Email Verification");
        mailMessage.setText("Please click the following link to verify your email: http://localhost:8080/korisnik/verify?id=" + user.getKorisnikId());

        javaMailSender.send(mailMessage);
    }
}
