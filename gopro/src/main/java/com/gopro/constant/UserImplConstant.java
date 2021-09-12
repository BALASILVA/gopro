package com.gopro.constant;

public class UserImplConstant {
    public static final String USERNAME_ALREADY_EXISTS = "Username already exists";
    public static final String EMAIL_ALREADY_EXISTS = "Email already registered";
    public static final String TECHINICAL_ERROR_OCCURED = "TECHINICAL ERROR OCCURED";
    public static final String NO_USER_FOUND_BY_USERNAME = "No user found by username: ";
    public static final String NO_USER_FOUND_BY_ID = "No user found by id: ";
    public static final String FOUND_USER_BY_USERNAME = "Returning found user by username: ";
    public static final String DEFAULT_USER_IMAGE_PATH = "/user/image/profile/temp";
    
    public static final Long PARENT_USER_ID_SUPER_ADMIN = 0L;
    
    //1	Y	ROLE_SUPER_ADMIN
    //2	Y	MANAGER
    //3	Y	SUPERVISOR
    //4	Y	WORKERS
    
    public static final int SUPER_ADMIN_ROLE_ID = 1;
    public static final int HR_ROLE_ID = 2;
    public static final int MANAGER_ROLE_ID = 20;
    public static final int SUPERVISOR_ROLE_ID = 30;
    public static final int WORKER_ROLE_ID = 40;

}
