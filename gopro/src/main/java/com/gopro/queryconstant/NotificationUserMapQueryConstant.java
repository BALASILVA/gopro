package com.gopro.queryconstant;

public interface NotificationUserMapQueryConstant {

	public static final String FIND_ALL_NOTIFICATION_USER_MAP_BY_NOTIFICATION_MESSAGEMAP_ID = "select notusr.notificationUserMapId,notusr.sendTo,usr.firstName,usr.lastName,notusr.mappingType,notusr.isFavorite,notusr.isReaded,notusr.readTime,notusr.NotificationMessageMapId from NotificationUserMap notusr INNER JOIN User usr on notusr.sendTo = usr.id where notusr.notificationMessageMapId = :notificationMessageMapId";

}
