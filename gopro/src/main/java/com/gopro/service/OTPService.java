package com.gopro.service;

import com.gopro.bene.OTPDto;

public interface OTPService {

	boolean sendEmailOTP(OTPDto otpDto);

	boolean isValidOTP(String email, Long otp);

}
