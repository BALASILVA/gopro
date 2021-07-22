package com.gopro.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.gopro.bene.Notification;
import static com.gopro.queryconstant.QueryConstant.*;

public interface NotificationRepo extends PagingAndSortingRepository<Notification, Long> {

	@Query(value = FIND_NOTIFICATION_BY_INVOICE_ID, nativeQuery = true)
	Notification findNotificationByInvoiceId(@Param("invoiceId") Long invoiceId);

	@Query(name = "findAllNotification")
	Page<Notification> getAllNotifications(@Param("logInUser") Long logInUser, @Param("sendFrom") Long sendFrom,
			@Param("sendTo") Long sendTo, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate,
			@Param("searchKeyWord") String searchKeyWord, Pageable pageable);

	@Query( name = "findNotificationById")
	Notification findNotificationById(@Param("logInUser") Long logInUser, @Param("notificationId") Long notificationId);
	
	@Query(value = COUNT_NOTIFICATION_AFTER__SEARCH, nativeQuery = true)
	Integer getUpdateAllNotification(@Param("logInUser") Long logInUser, @Param("sendFrom") Long sendFrom,
			@Param("sendTo") Long sendTo, @Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("isFavorite") boolean isFavorite, @Param("isReaded") boolean isReaded,
			@Param("searchKeyWord") String searchKeyWord,
			@Param("notificationLatUpdateDate") Date notificationLatUpdateDate);

	@Modifying
	@Query(value = UPDATE_NOTIFICATION_LAST_UPDATE_DATE_TIME , nativeQuery = true)
	int updateLastUpdateTimeDate(@Param("date")Date date,@Param("notificationId") Long notificationId);

}
