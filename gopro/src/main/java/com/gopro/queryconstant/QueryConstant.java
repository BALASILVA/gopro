package com.gopro.queryconstant;

public interface QueryConstant {

	// Invoice Module
	public static final String FIND_ALL_INVOICE_SEARCH = "SELECT inv.invoiceId,inv.customerMobileNo,inv.noOfProduct,inv.totalPrice, inv.paymentType, inv.date,inv.shopId,inv.userId,inv.userName,inv.remarks FROM Invoice inv WHERE inv.invoiceId  = Ifnull(:invoiceId, inv.invoiceId) AND (inv.customerMobileNo = Ifnull(:customerMobileNo, inv.customerMobileNo) OR inv.customerMobileNo is null) AND Upper(inv.noOfProduct)= Upper(Ifnull(:noOfProduct, inv.noOfProduct)) AND inv.totalPrice BETWEEN Ifnull(:startPrice, inv.totalPrice) AND Ifnull(:endPrice, inv.totalPrice) AND ((date >= :fromDate OR :fromDate IS NULL) AND (date <= :toDate OR :toDate IS NULL) ) AND Upper(inv.paymentType) = Upper(Ifnull(:paymentType, inv.paymentType)) AND Upper(inv.userName) = Upper(Ifnull(:username, inv.userName)) AND Concat_ws(inv.invoiceId,inv.customerMobileNo,inv.noOfProduct, inv.totalPrice, inv.date, inv.paymentType, inv.userName) LIKE :searchKeyWord AND inv.shopId = :shopId";
	
	// NotificationModule
	public static final String FIND_NOTIFICATION_BY_INVOICE_ID = "select * from notification where invoiceId=:invoiceId";

	//Unchanged Query
	public static final String FIND_ALL_NOTIFICATION_SEARCH = "SELECT * FROM   notification notif INNER JOIN notificationmessagemap notmes on notif.notificationId = notmes.notificationId INNER JOIN notificationusermap notusr on notusr.notificationMessageMapId = notmes.notificationMessageMapId WHERE (notmes.sendFrom = :logInUser OR notusr.sendTo = :logInUser) AND notmes.sendFrom = Ifnull(:sendFrom,notmes.sendFrom) AND notusr.sendTo= Ifnull(:sendTo,notusr.sendTo) AND notusr.isFavorite= Ifnull(:isFavorite,notusr.isFavorite) AND notusr.isReaded= Ifnull(:isReaded,notusr.isReaded) AND ( ( notmes.time >= :fromDate OR :fromDate IS NULL ) AND ( notmes.time <= :toDate OR :toDate IS NULL ) ) AND Concat_ws(notif.Subject,notmes.message,notmes.time) LIKE :searchKeyWord group by notif.notificationId order by notif.notificationLatUpdateDate desc";

	//public static final String FIND_ALL_NOTIFICATION_SEARCH = "SELECT notification.* FROM   notification notif INNER JOIN notif.notificationMessageMap notmes on notif.notificationId = notmes.notificationId INNER JOIN notificationUserMap notusr on notusr.notificationMessageMapId = notmes.notificationMessageMapId WHERE (notmes.sendFrom = :logInUser OR notusr.sendTo = :logInUser) AND notmes.sendFrom = Ifnull(:sendFrom,notmes.sendFrom) AND notusr.sendTo= Ifnull(:sendTo,notusr.sendTo) AND notusr.isFavorite= Ifnull(:isFavorite,notusr.isFavorite) AND notusr.isReaded= Ifnull(:isReaded,notusr.isReaded) AND ( ( notmes.time >= :fromDate OR :fromDate IS NULL ) AND ( notmes.time <= :toDate OR :toDate IS NULL ) ) AND Concat_ws(notif.Subject,notmes.message,notmes.time) LIKE :searchKeyWord group by notif.notificationId order by notif.notificationLatUpdateDate desc";
	
	public static final String COUNT_NOTIFICATION_AFTER__SEARCH ="SELECT count(*) from (SELECT COUNT(notif.notificationid) FROM   notification notif INNER JOIN notificationmessagemap notmes ON notif.notificationid = notmes.notificationid INNER JOIN notificationusermap notusr ON notusr.notificationmessagemapid = notmes.notificationmessagemapid WHERE  ( notmes.sendfrom = :logInUser OR notusr.sendto = :logInUser ) AND notmes.sendfrom = Ifnull(:sendFrom, notmes.sendfrom) AND notusr.sendto = Ifnull(:sendTo, notusr.sendto) AND notusr.isfavorite = Ifnull(:isFavorite, notusr.isfavorite) AND notusr.isreaded = Ifnull(:isReaded, notusr.isreaded) AND ( ( notmes.time >= :fromDate OR :fromDate IS NULL ) AND ( notmes.time <= :toDate OR :toDate IS NULL ) ) AND Concat_ws(notif.subject, notmes.message, notmes.time) LIKE :searchKeyWord AND notif.notificationLatUpdateDate > :notificationLatUpdateDate GROUP  BY notif.notificationid ORDER  BY notif.notificationlatupdatedate DESC )T";
	
	public static final String UPDATE_NOTIFICATION_LAST_UPDATE_DATE_TIME ="update notification SET notificationLatUpdateDate = :date where notificationId=:notificationId";

	public static final String FIND_USERS_FOR_SEND_MAIL = "select usr.id,usr.username,usr.firstname,usr.lastname,usr.phonenumber,usr.profileimageurl from user usr where usr.parentuserid = :parentUserId OR usr.id = :parentUserId group by usr.id";

}
