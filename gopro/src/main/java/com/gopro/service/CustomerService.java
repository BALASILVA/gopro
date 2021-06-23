package com.gopro.service;

import com.gopro.bene.Customer;

public interface CustomerService {

	Customer findById(Long customerId);

	Customer findByCustomerMobileNo(Long mobileNo);
}
