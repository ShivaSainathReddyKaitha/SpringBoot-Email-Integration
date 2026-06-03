package com.project.EmailIntegration.EmailIntegration.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.EmailIntegration.EmailIntegration.DTO.EmailRequest;
import com.project.EmailIntegration.EmailIntegration.Service.EmailService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping
public class EmailController {
	
	@Autowired
	private EmailService service;
	
	@GetMapping("/sendEmail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest requestDTO) {
		return new ResponseEntity(service.SimpleEmail(requestDTO),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/sendEmail/attachment")
	public ResponseEntity<?> includeAttachment(@RequestBody EmailRequest requestDTO) throws MessagingException {
		return new ResponseEntity(service.MimeMessageEmail(requestDTO), HttpStatus.ACCEPTED);
	}

}
