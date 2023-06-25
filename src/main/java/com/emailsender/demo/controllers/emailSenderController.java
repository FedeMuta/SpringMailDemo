package com.emailsender.demo.controllers;

import com.emailsender.demo.services.emailSenderService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class emailSenderController {

    @Autowired
    private emailSenderService emailSenderService;

    @PostMapping("/sendEmails")
    public String enviarEmailToList(@RequestParam List<String> emailList,
            @RequestParam String subject,
            @RequestParam String template) {

        if (emailList.isEmpty() || isNullOrEmpty(subject) || isNullOrEmpty(template)) {
            return "Los parámetros de entrada no pueden ser nulos o vacíos";
        }
        List<String> errorLogg = new ArrayList<>();
        List<String> emailsToSend = new ArrayList<>();
        for (String email : emailList) {
            if (!isValidEmail(email)) {
                errorLogg.add(email);
            } else if (isValidEmail(email)) {
                emailsToSend.add(email);
            }
        }
        if (emailsToSend.isEmpty()) {
            return "No se encontraron mails con formato válido";
        }
        emailSenderService.sendEmailToList(emailsToSend, subject, template);
        if (errorLogg.isEmpty()) {
            return "Mails enviados correctamente";
        }
        return "Algunos mails enviados correctamente.\nEstos tienen el formato incorrecto: " + errorLogg.toString();
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isValidEmail(String email) {
        // Expresión regular para validar el formato del correo electrónico
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);

        return pattern.matcher(email).matches();
    }

}
