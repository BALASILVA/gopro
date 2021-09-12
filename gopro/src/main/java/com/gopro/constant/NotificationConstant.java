package com.gopro.constant;

import static com.gopro.constant.UserImplConstant.*;

import java.util.ArrayList;
import java.util.List;

public class NotificationConstant {

	public static final int INVOICE_REPRINT_NOTIFICTION_TO_SEND_ROLE_ARR[] = { SUPER_ADMIN_ROLE_ID, MANAGER_ROLE_ID,
			SUPER_ADMIN_ROLE_ID };

	public static final String NOTIFICATION_MAPPING_TYPE_FROM= "FROM";
	
	public static final String NOTIFICATION_MAPPING_TYPE_TO= "TO";
	
	public static final String NOTIFICATION_MAPPING_TYPE_CC= "CC";
	
	public static final String NOTIFICATION_MAPPING_TYPE_REPLYALL_REFERENCE = "REPLYALL_REFERENCE";
	
	public static final String NOTIFICATION_MAPPING_TYPE_BCC= "BCC";
	
	public static final String NOTIFICATION_TYPE_CREATE_NEW= "CREATE_NEW";
	
	public static final String NOTIFICATION_TYPE_COMPOSE_NEW= "COMPOSE_NEW";
	
	public static final String NOTIFICATION_TYPE_CREATE_FORWARD= "FORWARD";

	public static final String NOTIFICATION_TYPE_REPRINT= "REPRINT";
	
	public static final String NOTIFICATION_TYPE_OUT_OF_STOCK= "OUT_OF_STOCK";
	
	public static final String MODULE_NAME_ALL = "ALL";

	public static final String MODULE_NAME_INBOX = "INBOX";

	public static final String MODULE_NAME_SEND = "SEND";

	public static final String MODULE_NAME_STARED = "STARED";

	public static final String MODULE_NAME_IMPORTANT = "IMPORTANT";
	
	
	
	public static final List<Integer> INVOICE_REPRINT_NOTIFICTION_TO_SEND_ROLE_LIST =
			new ArrayList<Integer>(){
				private static final long serialVersionUID = 1L;
			{
			add(SUPER_ADMIN_ROLE_ID);
			add(MANAGER_ROLE_ID);
			add(SUPER_ADMIN_ROLE_ID);
			}
		};
		
	public static final  List<Integer>  ADMIN_MANAGER_ROLEID = new ArrayList<Integer>(){
			private static final long serialVersionUID = 1L;
		{		
		add(MANAGER_ROLE_ID);
		add(SUPER_ADMIN_ROLE_ID);
		}
	};
}
