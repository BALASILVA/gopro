package com.gopro.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.gopro.bene.OTPDto;

public interface OTPRepo extends PagingAndSortingRepository<OTPDto, Long> {

	@Query(value = "select * from otpdto where date >= :date AND email = :email AND otp = :otp", nativeQuery = true)
	OTPDto findOTPByMail(@Param("email") String email, @Param("date") Date date, @Param("otp") Long otp);

}
