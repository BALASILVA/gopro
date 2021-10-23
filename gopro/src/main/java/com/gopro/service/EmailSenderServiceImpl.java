package com.gopro.service;

import javax.mail.SendFailedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gopro.bene.OTPDto;
import com.gopro.exception.domain.EmailNotFoundException;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
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
			LOGGER.error("Exception occured"+ ex.getMessage());
			return false;
		}
	}

	@Override
	public boolean sendEmailOTP(OTPDto otpDto) throws Exception {					
		try {
			LOGGER.info("Try To send Email");
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailAddress);
			message.setTo(otpDto.getEmail());
			message.setText("Hi "+otpDto.getUserName()+"/n Your OTP to login "+otpDto.getOtp()+"/n/n");
			message.setSubject("OTP");
			mailSender.send(message);
			LOGGER.info("Sucessfuly Email Send");
		} catch (Exception ex) {
			LOGGER.error("Exception occured"+ ex.getMessage());
			throw new SendFailedException("Fail To Send Email, Check Email Address");			
		}
		return true;
	}
}
