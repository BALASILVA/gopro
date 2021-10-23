package com.gopro.validation;

import static com.gopro.constant.ExceptionMessage.INPUT_MISSMATCH_ERROR_OCCURED;

import java.util.InputMismatchException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gopro.bene.OTPDto;

@Component
public class OTPValidation {

	Logger LOGGER = LoggerFactory.getLogger(UserValidation.class);

	CommanValidation commanValidation;

	@Autowired
	public OTPValidation(CommanValidation commanValidation) {
		this.commanValidation = commanValidation;
	}

	public boolean validateOTP(OTPDto otpDto) {
		String email = otpDto.getEmail();
		String userName = otpDto.getUserName();
		boolean emailVal = false;
		boolean userNameVal = false;

		if (commanValidation.isNotEmpty(userName) && commanValidation.checkMaixmummLength(userName)
				&& commanValidation.checkSecurity(userName)) {
			userNameVal = true;
		} else {
			LOGGER.error("Invalid User Name :: " + userName);
			throw new InputMismatchException("Invalid User Name");
		}

		if (commanValidation.isNotEmpty(email) && commanValidation.checkMaixmummLength(email)
				&& commanValidation.checkSecurity(email) && commanValidation.isVaildEmail(email)) {
			emailVal = true;
		} else {
			LOGGER.error("Invalid  Email:: " + email);
			throw new InputMismatchException("Invalid  Email");
		}

		if (userNameVal && emailVal)
			return true;
		else {			
			throw new InputMismatchException(INPUT_MISSMATCH_ERROR_OCCURED);
		}
	}

}
