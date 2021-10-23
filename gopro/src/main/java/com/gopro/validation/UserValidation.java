package com.gopro.validation;

import java.util.InputMismatchException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gopro.bene.Role;
import com.gopro.bene.Shop;
import com.gopro.bene.User;
import com.gopro.bene.VaildationResponse;

import static com.gopro.constant.ExceptionMessage.*;

@Component
public class UserValidation {

	Logger LOGGER = LoggerFactory.getLogger(UserValidation.class);

	CommanValidation commanValidation;

	@Autowired
	public UserValidation(CommanValidation commanValidation) {
		this.commanValidation = commanValidation;
	}

	public boolean validationUserForRegister(User user) throws Exception {

		LOGGER.info(
				"Validation User INFO with User Name :: " + user.getFirstName() + "  || Email ::" + user.getEmail());

		String firstName = user.getFirstName();
		Shop shop = user.getShopList().get(0);
		String email = user.getEmail();
		String PhoneNumber = user.getPhoneNumber();
		String password = user.getPassword();
		Long otp = user.getOtp();

		boolean firstNameVal = false;
		boolean shopVal = false;
		boolean emailVal = false;
		boolean phoneVal = false;
		boolean passwordVal = false;
		boolean otpVal = false;

		if (commanValidation.isNotEmpty(firstName) && commanValidation.checkMaixmummLength(firstName)
				&& commanValidation.checkSecurity(firstName)) {
			firstNameVal = true;
		} else {
			LOGGER.error("Invalid User Name " + firstName);
			throw new InputMismatchException("Invalid User Name ");
		}

		if (commanValidation.isNotEmpty(shop.getShopName()) && commanValidation.checkMaixmummLength(shop.getShopName())
				&& commanValidation.checkSecurity(shop.getShopName())) {
			shopVal = true;
		} else {
			LOGGER.error("Invalid Shop Name " + shop.getShopName());
			throw new InputMismatchException("Invalid Shop Name");
		}

		if (commanValidation.isNotEmpty(email) && commanValidation.checkMaixmummLength(email)
				&& commanValidation.checkSecurity(email) && commanValidation.isVaildEmail(email)) {
			emailVal = true;
		} else {
			LOGGER.error("Invalid Email " + email);
			throw new InputMismatchException("Invalid Email");
		}

		if (commanValidation.checkMaixmummLength(PhoneNumber) && commanValidation.checkSecurity(PhoneNumber)
				&& commanValidation.isNumber(PhoneNumber)) {
			phoneVal = true;
		} else {
			LOGGER.error("Invalid Phone Number " + PhoneNumber);
			throw new InputMismatchException("Invalid Phone Number");
		}

		if (commanValidation.isNotEmpty(password) && commanValidation.checkMaixmummLength(password)
				&& commanValidation.checkSecurity(password) && commanValidation.isVaildPassword(password)) {
			passwordVal = true;
		} else {
			LOGGER.error("Invalid Password " + password);
			throw new InputMismatchException("Invalid Password");
		}

		if (commanValidation.isNumber(otp) && commanValidation.checkOTPLength(otp)
				&& commanValidation.checkSecurity(PhoneNumber)) {
			otpVal = true;
		} else {
			LOGGER.error("Invalid OTP " + otp);
			throw new InputMismatchException("Invalid OTP");
		}

		if (firstNameVal && shopVal && emailVal && phoneVal && passwordVal && otpVal)
			return true;
		else {
			LOGGER.error("Vaildation Failed Wile Register With user name :: " + firstName);
			throw new InputMismatchException(INPUT_MISSMATCH_ERROR_OCCURED);
		}

	}

	public void validateChangePassword(String password) {

		boolean passwordVal = false;

		if (commanValidation.isNotEmpty(password) && commanValidation.checkMaixmummLength(password)
				&& commanValidation.checkSecurity(password) && commanValidation.isVaildPassword(password)) {
			passwordVal = true;
		} else {
			LOGGER.error("Invalid Password " + password);
			throw new InputMismatchException("Invalid Password");
		}

	}

	public void validateLogInUserDetail(String email, String password) {
		boolean passwordVal = false;
		boolean emailVal = false;

		if (commanValidation.isNotEmpty(email) && commanValidation.checkMaixmummLength(email)
				&& commanValidation.checkSecurity(email) && commanValidation.isVaildEmail(email)) {
			emailVal = true;
		} else {
			LOGGER.error("Invalid Email " + email);
			throw new InputMismatchException("Invalid Email");
		}

		if (commanValidation.isNotEmpty(password) && commanValidation.checkMaixmummLength(password)
				&& commanValidation.checkSecurity(password) && commanValidation.isVaildPassword(password)) {
			passwordVal = true;
		} else {
			LOGGER.error("Invalid Password " + password);
			throw new InputMismatchException("Invalid Password");
		}
	}

	public boolean validateAddSubUserDetails(String firstName, String lastName, String email, String phoneNumber,
			String addressLine1, List<Shop> shopList, Role roleObject, String remarks) {

		boolean firstNameVal = false;
		boolean shopVal = false;
		boolean emailVal = false;
		boolean phoneVal = false;
		boolean passwordVal = false;
		boolean otpVal = false;

		if (commanValidation.isNotEmpty(firstName) && commanValidation.checkMaixmummLength(firstName)
				&& commanValidation.checkSecurity(firstName)) {
			firstNameVal = true;
		} else {
			LOGGER.error("Invalid User Name " + firstName);
			throw new InputMismatchException("Invalid User Name ");
		}

		if (shopList.size() > 0) {
			shopVal = true;
		} else {
			LOGGER.error("No Shop Selected Shop List Size is :: " + shopList.size());
			throw new InputMismatchException("Select Shop For New User");
		}

		if (commanValidation.isNotEmpty(email) && commanValidation.checkMaixmummLength(email)
				&& commanValidation.checkSecurity(email) && commanValidation.isVaildEmail(email)) {
			emailVal = true;
		} else {
			LOGGER.error("Invalid Email " + email);
			throw new InputMismatchException("Invalid Email");
		}

		if (commanValidation.checkMaixmummLength(phoneNumber) && commanValidation.checkSecurity(phoneNumber)
				&& commanValidation.isNumber(phoneNumber)) {
			phoneVal = true;
		} else {
			LOGGER.error("Invalid Phone Number " + phoneNumber);
			throw new InputMismatchException("Invalid Phone Number");
		}

		if (roleObject.getRoleId() > 0) {
			phoneVal = true;
		} else {
			LOGGER.error("Role Id Less Then 0 || RoleId :: " + roleObject.getRoleId());
			throw new InputMismatchException("Select Role For New User");
		}

		if (commanValidation.checkMaixmummLength(remarks) && commanValidation.checkSecurity(remarks)) {

		} else {
			LOGGER.error("Invalid Remarks Length :: " + remarks.length());
			throw new InputMismatchException("Notes Length Too Long");
		}

		if (commanValidation.checkMaixmummLength(addressLine1) && commanValidation.checkSecurity(addressLine1)) {

		} else {
			LOGGER.error("addressLine1 Remarks Length :: " + remarks.length());
			throw new InputMismatchException("Address Length Too Long");
		}

		return true;
	}
}
