package com.emailsender.demo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.emailsender.demo.services.emailSenderService;

import jakarta.mail.MessagingException;

@SpringBootApplication
public class DemoApplication {
	@Autowired
	emailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerWhenStarts() throws MessagingException, IOException {
		senderService.sendEmail("fedemuta@gmail.com", "Email de prueba", "Send");
	}

}
