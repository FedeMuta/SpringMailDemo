package com.emailsender.demo.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class emailSenderService {

    private static final String FROM_EMAIL_ADDRESS = "noresponder@show-room.com.ar";

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * Envía un correo electrónico con el contenido del archivo de plantilla
     * especificado.
     *
     * @param toEmail      dirección de correo electrónico del destinatario
     * @param subject      asunto del correo electrónico
     * @param templateName nombre del archivo de plantilla en la carpeta de recursos
     * @return mensaje de éxito o error
     */
    public String sendEmail(String toEmail, String subject, String templateName) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(FROM_EMAIL_ADDRESS);
            helper.setTo(toEmail);
            helper.setSubject(subject);

            String htmlMsg = new String(resourceLoader.getResource("classpath:templates/" + templateName + ".html")
                    .getInputStream().readAllBytes());
            helper.setText(htmlMsg, true);

            mailSender.send(message);

            return "Email enviado";
        } catch (MessagingException | IOException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String sendEmailToList(List<String> emailList, String subject, String templateName) {

        List<String> logg = new ArrayList<>();
        for (String toEmail : emailList) {
            var returnTxt = sendEmail(toEmail, subject, templateName);
            logg.add(returnTxt);
        }
        return logg.toString();
    }

}