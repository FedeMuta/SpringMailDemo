package com.emailsender.demo.services;

import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class emailSenderService {
    
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String subject) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        
        helper.setFrom("noresponder@show-room.com.ar");
        helper.setTo(toEmail);
        helper.setSubject(subject);
        
        String htmlMsg = "<h1>Email de prueba</h1>";
        helper.setText(htmlMsg, true);

        mailSender.send(message);

        System.out.println("Email enviado");

    }


}
