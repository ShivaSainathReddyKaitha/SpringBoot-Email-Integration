package com.project.EmailIntegration.EmailIntegration.Service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.project.EmailIntegration.EmailIntegration.DTO.EmailRequest;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;


@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String from;
	
	public String SimpleEmail(EmailRequest request) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(from);
		mail.setTo(request.getTo());
		mail.setSubject(request.getSubject());
		mail.setText(request.getBody());
		
		javaMailSender.send(mail);
		return "Email without any attachment has been sent";
	}
	
	public String MimeMessageEmail(EmailRequest request) throws MessagingException {
		MimeMessage mime = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mime, true);
		helper.setFrom(from);
		helper.setTo(request.getTo());
		helper.setSubject(request.getSubject());
		helper.setText(request.getBody(), true);
		
		FileSystemResource file = new FileSystemResource(new File(request.getAttachment()));
		helper.addAttachment(file.getFilename(), file);
		
		javaMailSender.send(mime);
		return "Email with attachment has been sent";
	}

}
