package com.emailsender.demo.controllers;

import com.emailsender.demo.services.emailSenderService;

import jakarta.mail.MessagingException;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class emailSenderController {

    @Autowired
    private emailSenderService emailSenderService;

    @PostMapping("/sendEmail")
    public ResponseEntity<String> enviarEmail(@RequestParam String email,
            @RequestParam String subject,
            @RequestParam String template) throws MessagingException, IOException {
        
            String result = emailSenderService.sendEmail(email, subject, template);
            return ResponseEntity.ok(result);
        } 
}


