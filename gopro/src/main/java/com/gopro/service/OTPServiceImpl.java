package com.gopro.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gopro.bene.OTPDto;
import com.gopro.repository.OTPRepo;

@Service
public class OTPServiceImpl implements OTPService {

	private OTPRepo otpRepo;
	private EmailSenderService emailSenderService;

	@Autowired
	public OTPServiceImpl(OTPRepo otpRepo, EmailSenderService emailSenderService) {
		this.otpRepo = otpRepo;
		this.emailSenderService = emailSenderService;
	}

	@Override
	public boolean sendEmailOTP(OTPDto otpDto) {

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
		emailSenderService.sendEmailOTP(savedotpDTO);
		return true;
		
	}

	private Long generateFourDigitOTP() {

		long leftLimit = 100000L;
		long rightLimit = 999999L;
		long generatedOTP = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
		System.out.println(generatedOTP);
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
