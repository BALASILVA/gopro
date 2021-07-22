package com.gopro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gopro.bene.NotificationUserMap;

public interface NotificationUserMapRepo extends JpaRepository<NotificationUserMap, Long> {
		
	@Query(nativeQuery = true, name = "findAllNotificationUserMap")
	List<NotificationUserMap> findAllMapedUserByMeesageMapId(@Param(value = "notificationMessageMapId")Long notificationMessageMapId);

	@Modifying
	@Query(value = "UPDATE notificationusermap set isfavorite=:isFavorite where sendTo = :logInUserId and notificationMessageMapId = :notificationMessageMapId", nativeQuery = true)
	void toggleFav(@Param("isFavorite") boolean isFavorite,@Param("logInUserId") Long logInUserId,@Param("notificationMessageMapId") Long notificationMessageMapId);
	
	@Modifying
	@Query(value = "UPDATE notificationusermap set isReaded= 1 where sendTo = :userId and notificationMessageMapId in :notificationMessageIdList", nativeQuery = true)
	int updateReadedFlag(@Param("userId")Long userId,@Param("notificationMessageIdList") List<Long> notificationMessageIdList);

	@Modifying
	@Query(value = "insert into notificationusermap (sendTo,mappingType,notificationmessagemapid) select :sendTo,:mappingType,:notificationMessageMapId Where not exists(select 1 from notificationusermap where sendTo=:sendTo AND notificationMessageMapId=:notificationMessageMapId)", nativeQuery = true)
	void saveIfNotExits(@Param("sendTo")Long sendTo,@Param("mappingType") String mappingType,@Param("notificationMessageMapId") Long notificationMessageMapId);

	@Modifying
	@Query(value = "UPDATE notificationusermap SET isDeleted= 1  WHERE sendTo = :userId AND notificationMessageMapId = :notificationMessageMapId", nativeQuery = true)
	int deletMessage(@Param("userId")Long userId,@Param("notificationMessageMapId") Long notificationMessageMapId);

	@Modifying
	@Query(value = "UPDATE notificationusermap notusr inner join  notificationMessageMap notmes on notmes.notificationMessageMapId = notusr.notificationMessageMapId SET notusr.isReaded = :isReaded WHERE notmes.notificationId in ( :notificationId ) AND notusr.sendTo = :userId", nativeQuery = true)
	int updateReadMessageFlag(@Param("notificationId") List<Long> notificationId,@Param("userId") Long userId,@Param("isReaded") boolean isReaded);

}
