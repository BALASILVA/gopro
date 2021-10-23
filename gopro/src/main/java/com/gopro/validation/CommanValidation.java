package com.gopro.validation;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import static com.gopro.constant.CommanConstants.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommanValidation {

	Logger LOGGER = LoggerFactory.getLogger(UserValidation.class);

	public boolean isNotEmpty(String input) {
		if (StringUtils.isNotEmpty(input)) {
			return true;
		}
		return false;
	}

	public boolean checkMaixmummLength(String input) {
		if (input.length() <= MAXIMUM_USER_INPUT_LENGTH) {
			return true;
		}
		LOGGER.info("input length to long for " + input + "with length " + input.length());
		return false;
	}

	public boolean isVaildEmail(String input) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (matcher.matches())
			return true;
		LOGGER.info("Email Id not valid " + input);
		return false;
	}

	public boolean checkSecurity(String input) {
		// TODO If any need to add for security
		// Added in all input validation
		return true;
	}

	public boolean isNumber(String input) {
		if (StringUtils.isNumeric(input)) {
			return true;
		}
		LOGGER.info("Not Numeric Validation Failed " + input);
		return false;
	}

	public boolean checkMaixmummLength(Long input) {
		if (input + "".length() <= MAXIMUM_USER_INPUT_LENGTH) {
			return true;
		}
		LOGGER.info("Input length to long for " + +input + " Validation Failed with length " + input + "".length());
		return false;
	}

	public boolean checkOTPLength(Long otp) {
		if (otp.toString().length() == OTP_LENGTG) {
			return true;
		}
		LOGGER.info("Invalid OTP " + otp + " Length Mismatch with length"+otp.toString());
		return false;
	}

	public boolean isNumber(Long input) {
		return true;
	}

	public boolean isVaildPassword(String password) {
		if (password.length() >= PASSWORD_MIN_LENGTG) {
			return true;
		}
		LOGGER.info("Invalid Password ::  " + password + " || Length Mismatch");
		return false;
	}

}
