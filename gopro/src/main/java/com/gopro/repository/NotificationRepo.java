package com.gopro.repository;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gopro.bene.Notification;
import static com.gopro.queryconstant.QueryConstant.*;

public interface NotificationRepo extends JpaRepository<Notification, Long> {

	@Query(value = FIND_NOTIFICATION_BY_INVOICE_ID, nativeQuery = true)
	Notification findNotificationByInvoiceId(@Param("invoiceId") Long invoiceId);

	@Query(value = FIND_ALL_NOTIFICATION_SEARCH, nativeQuery = true)
	List<Notification> fingAllNotifications(@Param("logInUser") Long logInUser, @Param("sendFrom") Long sendFrom,
			@Param("sendTo") Long sendTo, @Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("isFavorite") boolean isFavorite, @Param("isReaded") boolean isReaded,
			@Param("searchKeyWord") String searchKeyWord, Pageable pageable);

	@Query(value = COUNT_NOTIFICATION_AFTER__SEARCH, nativeQuery = true)
	Integer getUpdateAllNotification(@Param("logInUser") Long logInUser, @Param("sendFrom") Long sendFrom,
			@Param("sendTo") Long sendTo, @Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("isFavorite") boolean isFavorite, @Param("isReaded") boolean isReaded,
			@Param("searchKeyWord") String searchKeyWord,
			@Param("notificationLatUpdateDate") Date notificationLatUpdateDate);

}
