package com.gopro.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gopro.bene.NotificationMessageMap;
import static com.gopro.queryconstant.NotificationMessageMapQueryConstant.*;

public interface NotificationMessageMapRepo extends JpaRepository<NotificationMessageMap, Long> {
	
	@Query(nativeQuery = true, name = "findLastMessageByNotificationId")
	List<NotificationMessageMap> findLastMeesageOfNotificationId(@Param(value = "notificationId")Long notificationId,@Param(value = "logInUserId") Long logInUserId);

		
	@Query(nativeQuery = true, name = "findAllMessageByNotificationId")
	List<NotificationMessageMap> findAllMeesageOfNotificationId(@Param(value = "notificationId")Long notificationId,@Param(value = "logInUserId") Long logInUserId);

	@Query(nativeQuery = true, name = "findAllMessageIdByNotificationId")
	List<NotificationMessageMap> findAllMessageIdOfNotificationId(Long notificationId);

	@Modifying
	@Query(value = "UPDATE notificationmessagemap   notmes inner join notificationusermap  notusr on notmes.notificationMessageMapId = notusr.notificationMessageMapId set notusr.isDeleted= 1 WHERE notusr.sendTo = :userId and notmes.notificationid=:notificationId", nativeQuery = true)
	int deleteAllMessage(@Param("notificationId")Long notificationId,@Param("userId") Long id);			

}
