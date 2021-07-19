package com.gopro.service;

import java.util.List;
import java.util.Set;

import com.gopro.bene.NotificationDetail;

public interface NotificationDetailService {

	List<NotificationDetail> getNotificationDetail(Long id, Long notificationId);

	boolean updateNotificationDetail(Set<Long> userIdToThisMail, Long  notificationId);

	boolean updateNotificationDetail(List<Long> userIdList, Long notificationId);

	boolean toogleFavNotification(Long notificationId);

	boolean toggleImportantNotification(Long notificationId);

	boolean makeFavNotificationInBulk(List<Long> notificationIdList);

	boolean makeImportantNotificationInBulk(List<Long> notificationIdList);

}
