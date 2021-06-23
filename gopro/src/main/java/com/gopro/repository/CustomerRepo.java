package com.gopro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gopro.bene.Customer;

public interface CustomerRepo extends JpaRepository<Customer , Long> {

	Customer findCustomerByCustomerMobileNo(Long mobileNo);

}
