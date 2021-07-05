package com.gopro.queryconstant;

public interface UserQueryConstant {
	
	public static final String FIND_USERS_FOR_SEND_MAIL = "select usr.Id,usr.username,usr.firstName,usr.lastName,usr.phoneNumber,usr.profileImageUrl from user usr where usr.parentUserId = :parentUserId OR usr.Id = :parentUserId group by usr.Id";

}
