package com.gopro.service;

import com.gopro.bene.OTPDto;

public interface OTPService {

	boolean sendEmailOTP(OTPDto otpDto) throws Exception;

	boolean isValidOTP(String email, Long otp);

}
