package com.gopro.service;

import java.util.List;

import com.gopro.bene.NotificationMessageMap;
import com.gopro.bene.NotificationUserMap;

public interface NotificationUserMapService {

	List<NotificationUserMap> findAllMapedUserByMeesageMapId(Long notificationMessageMapId);

	boolean updateUserMapForReplyAction(List<Long> userIdList, NotificationMessageMap notificationMessageMap);

	NotificationUserMap toggleFav(NotificationUserMap notificationUserMap);

	boolean updateReadedFlag(List<Long> notificationMessageIdList, Long userId);

	boolean deletMessage(Long messageId);

}
