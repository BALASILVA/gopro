package com.gopro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopro.bene.OTPDto;
import com.gopro.service.OTPService;

@RestController
@RequestMapping("/otp")
public class OTPController {
	
	public OTPService otpService;
	
	@Autowired
	public OTPController(OTPService otpService) {
		this.otpService = otpService;
	}

	@PostMapping(value = "/signup/email")
	public ResponseEntity<Boolean> sendOTP(@RequestBody OTPDto otpDto) {
		System.out.println(otpDto);
		boolean isMailSend = otpService.sendEmailOTP(otpDto);
		return new ResponseEntity<>(isMailSend, HttpStatus.OK);
	}

}
