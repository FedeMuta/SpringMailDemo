package com.emailsender.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.emailsender.demo.services.emailSenderService;

@SpringBootApplication
public class DemoApplication {
	@Autowired
	emailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
