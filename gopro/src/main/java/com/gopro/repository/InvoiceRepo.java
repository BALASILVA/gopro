package com.gopro.repository;


import java.util.Date;
import java.util.List;

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

	@Query(value = "select sum(totalprice)  from invoice where shopid = :defaultShopId and date >= :date", nativeQuery = true)
	Long getDefaultShopTodaySales(@Param("defaultShopId")Long defaultShopId,@Param("date") Date minimumTimeOfDate);
	
	@Query(value = "select sum(totalprice)  from invoice inv inner join user_shop_list shp on shp.user_id = :userId where shopid in (shp.shop_list_shopid) and date >= :date", nativeQuery = true)
	Long getUserAllShopTodaySales(@Param("userId")Long userId,@Param("date")Date minimumTimeOfDate);

	@Query(nativeQuery = true, name = "getDefaultShopSalesListByWeek")
	List<Invoice> getDefaultShopSalesListByWeek(@Param("defaultShopId")Long defaultShopId,@Param("oneWeekBeforeDate")Date oneWeekBeforeDate);

	@Query(nativeQuery = true, name = "getDefaultShopSalesListByMounth")
	List<Invoice> getDefaultShopSalesListByMounth(@Param("defaultShopId")Long defaultShopId,@Param("oneWeekBeforeDate")Date oneMounthBeforeDate);
	
}
