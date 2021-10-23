package com.gopro.validation;

import java.util.InputMismatchException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gopro.bene.Shop;

@Component
public class ShopValidation {
	
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	private CommanValidation commanValidation;
	
	@Autowired
	public ShopValidation(CommanValidation commanValidation) {
		super();
		this.commanValidation = commanValidation;
	}

	public void validate(Shop shop) {
		if (commanValidation.isNotEmpty(shop.getShopName()) && commanValidation.checkMaixmummLength(shop.getShopName())
				&& commanValidation.checkSecurity(shop.getShopName())) {
		} else {
			LOGGER.error("Invalid Shop Name " + shop.getShopName());
			throw new InputMismatchException("Invalid User Name ");
		}

		
		if (commanValidation.isNotEmpty(shop.getEmail()) && commanValidation.checkMaixmummLength(shop.getEmail())
				&& commanValidation.checkSecurity(shop.getEmail()) && commanValidation.isVaildEmail(shop.getEmail())) {
		} else {
			LOGGER.error("Invalid Email " + shop.getEmail());
			throw new InputMismatchException("Invalid Email");
		}

		if (commanValidation.checkMaixmummLength(shop.getPhone()) && commanValidation.checkSecurity(shop.getPhone())
				&& commanValidation.isNumber(shop.getPhone())) {
		} else {
			LOGGER.error("Invalid Phone Number " + shop.getPhone());
			throw new InputMismatchException("Invalid Phone Number");
		}

		if (commanValidation.checkMaixmummLength(shop.getAddressLineOne()) && commanValidation.checkSecurity(shop.getAddressLineOne())) {

		} else {
			LOGGER.error("addressLine1 Remarks Length :: " + shop.getAddressLineOne().length());
			throw new InputMismatchException("Address Length Too Long");
		}
		
	}

	
}
