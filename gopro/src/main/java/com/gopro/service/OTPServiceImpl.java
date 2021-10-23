package com.gopro.service;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gopro.bene.OTPDto;
import com.gopro.controller.UserController;
import com.gopro.repository.OTPRepo;
import com.gopro.validation.OTPValidation;

@Service
public class OTPServiceImpl implements OTPService {

	Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	private OTPRepo otpRepo;
	private EmailSenderService emailSenderService;
	private OTPValidation oTPValidation;

	@Autowired
	public OTPServiceImpl(OTPRepo otpRepo, EmailSenderService emailSenderService, OTPValidation oTPValidation) {
		this.otpRepo = otpRepo;
		this.emailSenderService = emailSenderService;
		this.oTPValidation = oTPValidation;
	}

	@Override
	public boolean sendEmailOTP(OTPDto otpDto) throws Exception {
		
		oTPValidation.validateOTP(otpDto);
		//Expire time plus minutes
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.MINUTE, 5);
		currentDate = cal.getTime();  //5 min Incremented time 
		
		OTPDto otpDTOCpy = new OTPDto();
		otpDTOCpy.setUserName(otpDto.getUserName());
		otpDTOCpy.setEmail(otpDto.getEmail());
		otpDTOCpy.setDate(currentDate);
		otpDTOCpy.setOtp(generateFourDigitOTP());
		OTPDto savedotpDTO = otpRepo.save(otpDTOCpy);
		LOGGER.info("Sending OTP To email Start:: " +otpDto.getEmail() );
		emailSenderService.sendEmailOTP(savedotpDTO);
		LOGGER.info("Sending OTP To email End:: " +otpDto.getEmail() );
		return true;
		
	}

	private Long generateFourDigitOTP() {

		long leftLimit = 100000L;
		long rightLimit = 999999L;
		long generatedOTP = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
		LOGGER.info("OTP Generated :: "+generatedOTP);
		return generatedOTP;
	}

	@Override
	public boolean isValidOTP(String email, Long otp) {				
		OTPDto otpDto = otpRepo.findOTPByMail(email,new Date(),otp);						
		if(otpDto == null)
			return false;
		return true;
	}

}
