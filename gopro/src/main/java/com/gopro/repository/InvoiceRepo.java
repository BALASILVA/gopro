package com.gopro.repository;


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
			@Param("noOfProduct") Long noOfProduct, @Param("startPrice") Long startPrice,
			@Param("endPrice") Long endPrice, @Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("paymentType") String paymentType, @Param("username") String username,
			@Param("searchKeyWord") String searchKeyWord, @Param("shopId") Long shopId,Pageable pageable);


	
}
