package com.emailsender.demo.services;

import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class emailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ResourceLoader resourceLoader;

    public String sendEmail(String toEmail, String subject, String templateName) throws MessagingException, IOException {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("noresponder@show-room.com.ar");
            helper.setTo(toEmail);
            helper.setSubject(subject);

            String htmlMsg = new String(resourceLoader.getResource("classpath:templates/" + templateName + ".html")
                    .getInputStream().readAllBytes());
            helper.setText(htmlMsg, true);

            mailSender.send(message);

            System.out.println("Email enviado");
            return "Email enviado";
        } catch (MessagingException | IOException e) {
            System.out.println("Error al enviar el email");
            e.printStackTrace();
            return "Error al enviar el email";
        }

    }

}
