package com.gopro.queryconstant;

public interface UserQueryConstant {
	
	public static final String FIND_USERS_FOR_SEND_MAIL = "select usr.Id,usr.username,usr.firstName,usr.lastName,usr.phoneNumber,usr.profileImageUrl from user usr where usr.parentUserId = :parentUserId OR usr.Id = :parentUserId group by usr.Id";

	//public static final String FIND_SHOPS_BY_PARENT_USER="SELECT R.shopid, R.shopname, R.phone,IFNULL(R.totalsale, 0) totalsale,IFNULL(R.todaysale, 0) todaysale FROM (SELECT SHP.shopid, SHP.shopname, SHP.phone, SHP.totalsale, Sum(INV.totalprice) AS todaysale FROM   shop SHP INNER JOIN invoice INV ON INV.shopid = SHP.shopid AND INV.date >= :date WHERE  SHP.userid = :parentUserId AND Concat_ws(SHP.shopid, SHP.shopname, SHP.totalsale, SHP.phone) LIKE :searchKeyWord group by SHP.shopid, SHP.shopname, SHP.phone UNION ALL SELECT SHP.shopid, SHP.shopname, SHP.phone, SHP.totalsale, 0 AS todaysale FROM   shop SHP INNER JOIN invoice INV ON INV.shopid <> SHP.shopid AND INV.date >= :date WHERE  SHP.userid = :parentUserId AND Concat_ws(SHP.shopid, SHP.shopname, SHP.totalsale, SHP.phone) LIKE :searchKeyWord )R group by R.shopid, R.shopname, R.phone";
	
	public static final String FIND_SHOPS_BY_PARENT_USER="SELECT * FROM shop WHERE userid = :parentUserId AND Concat_ws(shopid, shopname, totalsale, phone) LIKE :searchKeyWord";
	
}
