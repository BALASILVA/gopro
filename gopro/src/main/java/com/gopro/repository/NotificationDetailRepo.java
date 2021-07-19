package com.gopro.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gopro.bene.NotificationDetail;
import com.gopro.bene.User;

public interface NotificationDetailRepo extends JpaRepository<NotificationDetail, Long> {


	
	@Query(value = "select * from notificationDetail where userid=:logInUserId AND notificationId = :notificationId", nativeQuery = true)
	List<NotificationDetail> getNotificationDetail(@Param("logInUserId")Long id, @Param("notificationId")Long notificationId);

	@Modifying
	@Query(value = "insert into notificationdetail (userid,notificationId) select :userId,:notificationId Where not exists(select 1 from notificationdetail where userId=:userId AND notificationId=:notificationId)", nativeQuery = true)
	int updateNotificationDetail(@Param("userId") Long userId, @Param("notificationId")Long notificationId);

	@Modifying
	@Query(value = "UPDATE notificationdetail SET isfavorite = !isfavorite WHERE  userId = :userId AND notificationId = :notificationId",nativeQuery = true)
	void toogleFavNotification(@Param("notificationId")Long notificationId,@Param("userId") Long userId);
	
	@Modifying
	@Query(value = "UPDATE notificationdetail SET isimportant = !isimportant WHERE  userId = :userId AND notificationId = :notificationId",nativeQuery = true)
	void toggleImportantNotification(@Param("notificationId")Long notificationId,@Param("userId") Long userId);

	@Modifying
	@Query(value = "UPDATE notificationdetail SET isfavorite = 1 WHERE  userId = :userId AND notificationId in (:notificationId)",nativeQuery = true)
	int makeFavNotificationInBulk(@Param("notificationId")List<Long> notificationIdList,@Param("userId")  Long id);

	@Modifying
	@Query(value = "UPDATE notificationdetail SET isimportant = 1 WHERE  userId = :userId AND notificationId in (:notificationId)",nativeQuery = true)
	void makeImportantNotificationInBulk(@Param("notificationId")List<Long> notificationIdList,@Param("userId")  Long id);
	
}
