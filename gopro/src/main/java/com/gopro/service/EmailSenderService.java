package com.gopro.service;

import com.gopro.bene.OTPDto;
import com.gopro.exception.domain.EmailNotFoundException;

public interface EmailSenderService {

	boolean sendOTP(String toEmail,String body,String subject);

	boolean sendEmailOTP(OTPDto otpDto) throws Exception;
}
