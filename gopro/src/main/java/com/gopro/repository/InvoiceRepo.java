package com.gopro.repository;


import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.gopro.bene.Invoice;

public interface InvoiceRepo extends PagingAndSortingRepository<Invoice, Long> {
	
	@Query(nativeQuery = true, name = "findAllInvoiceMapping")
	Page<Invoice> findAllOfInvoice(
			@Param("invoiceId") Long invoiceId,
			@Param("customerMobileNo") Long customerMobileNo,
			@Param("noOfProduct") Long noOfProduct, @Param("startPrice") Double startPrice,
			@Param("endPrice") Double endPrice, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate,
			@Param("paymentType") String paymentType,
			@Param("searchKeyWord") String searchKeyWord, @Param("shopId") Long shopId,
			@Param("userId")Long userId,Pageable pageable);


	
}
