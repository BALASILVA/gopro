package com.gopro.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gopro.bene.Customer;
import com.gopro.repository.CustomerRepo;

import javassist.NotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepo customerRepo;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
	}


	@Override
	public Customer findById(Long customerId) {
		Optional<Customer> customer = customerRepo.findById(customerId);
		return customer.orElse(null);
	}


	@Override
	public Customer findByCustomerMobileNo(Long mobileNo) {
		Customer customer = null;
		try {
			customer = customerRepo.findCustomerByCustomerMobileNo(mobileNo);
		}
		catch(Exception ex){
			return null;
		}
		return customer;
	}
}
