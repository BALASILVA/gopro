package com.gopro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gopro.bene.OTPDto;
import com.gopro.exception.domain.EmailNotFoundException;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String emailAddress;

	@Override
	public boolean sendOTP(String toEmail, String body, String subject) {

		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailAddress);
			message.setTo(toEmail);
			message.setText(body);
			message.setSubject(subject);
			mailSender.send(message);
			return true;
		} catch (Exception ex) {
			System.out.println("Ex"+ex);
			return false;
		}
	}

	@Override
	public boolean sendEmailOTP(OTPDto otpDto) {
		
		System.out.println("sent dt"+otpDto.getEmail());
		
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailAddress);
			message.setTo(otpDto.getEmail());
			message.setText("Hi "+otpDto.getUserName()+"/n Your OTP to login "+otpDto.getOtp()+"/n/n");
			message.setSubject("OTP");
			mailSender.send(message);
		} catch (Exception ex) {
			return false;
		}
		return false;
	}
}
