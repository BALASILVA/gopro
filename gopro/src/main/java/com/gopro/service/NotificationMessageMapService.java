package com.gopro.service;

import java.util.List;

import com.gopro.bene.Notification;
import com.gopro.bene.NotificationMessageMap;
import com.gopro.bene.User;

public interface NotificationMessageMapService {

	NotificationMessageMap addNewnotificationMessageMap(NotificationMessageMap notificationMessageMap);

	List<NotificationMessageMap> findLastMeesageOfNotificationId(Notification notification, User logedInUser);

	List<NotificationMessageMap> findAllMeesageOfNotificationId(Long notificationId);

	List<NotificationMessageMap> findAllMessageIdFromNotificationId(Long notificationId);

	boolean deleteAllMessage(Long notificationId);

}
