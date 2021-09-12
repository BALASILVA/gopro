package com.gopro.queryconstant;

public interface NotificationMessageMapQueryConstant {

	public static final String  FIND_ALL_MESSAGE_ID_BY_NOTIFICATION_ID = "select notificationMessageMapId from notificationMessageMap WHERE notificationId = :notificationId";			
	
	public static final String  FIND_ALL_PARENT_MESSAGE_ID_BY_MESSAGE_ID = "with recursive cte (notificationmessagemapid, parentnotificationmessagemapid) as ( select    notificationmessagemapid, parentnotificationmessagemapid from notificationmessagemap where notificationmessagemapid = :notificationMessageMapId union all select    p.notificationmessagemapid, p.parentnotificationmessagemapid from       notificationmessagemap p inner join cte on p.notificationmessagemapid = cte.parentnotificationmessagemapid ) select * from cte";
	
	public static final String FIND_MESSAGE_BY_MESSAGE_ID = "SELECT notmes.notificationMessageMapId, notmes.parentNotificationMessageMapId, notmes.sendFrom, usr.firstName, usr.lastName, notmes.message, notmes.messageInnerHtml, notmes.notificationId, notmes.time FROM   notificationMessageMap notmes INNER JOIN USER usr ON notmes.sendFrom = usr.id INNER JOIN notificationUserMap notusr ON notusr.notificationMessageMapId = notmes.notificationMessageMapId WHERE notusr.sendTo = :logInUserId AND notusr.isdeleted <> 1 AND notmes.notificationMessageMapId = :notificationMessageMapId GROUP  BY notmes.notificationMessageMapId ORDER  BY notmes.time ASC";
	
	public static final String FIND_ALL_MESSAGE_BY_NOTIFICATION_ID  = "SELECT notmes.notificationMessageMapId, notmes.parentNotificationMessageMapId, notmes.sendFrom, usr.firstName, usr.lastName, notmes.message, notmes.messageInnerHtml, notmes.notificationId, notmes.time FROM   notificationMessageMap notmes INNER JOIN USER usr ON notmes.sendFrom = usr.id INNER JOIN notificationUserMap notusr ON notusr.notificationMessageMapId = notmes.notificationMessageMapId WHERE  notusr.sendTo = :logInUserId AND notusr.isdeleted <> 1 AND notmes.notificationId = :notificationId GROUP  BY notmes.notificationMessageMapId ORDER  BY notmes.time ASC";
	
	public static final String FIND_LAST_MESSAGE_BY_NOTIFICATION_ID = "select notmes.notificationMessageMapId,notmes.sendFrom, usr.firstName, usr.lastName,notmes.message,notmes.notificationId,notmes.time from notificationMessageMap notmes INNER JOIN User usr on notmes.sendFrom = usr.id INNER JOIN notificationUserMap notusr on (notusr.notificationMessageMapId = notmes.notificationMessageMapId AND notusr.isdeleted <> 1) WHERE (notmes.sendFrom = :logInUserId OR notusr.sendTo = :logInUserId) AND notmes.notificationId = :notificationId order by notmes.time desc LIMIT 1";

}
