package com.gopro.service;

import com.gopro.bene.Role;
import com.gopro.bene.Shop;
import com.gopro.bene.User;
import com.gopro.exception.domain.EmailExistException;
import com.gopro.exception.domain.UserNotFoundException;
import com.gopro.exception.domain.UsernameExistException;

import java.util.List;

public interface UserService {

    User register(String userName,String shopName, String email, String password) throws UserNotFoundException, UsernameExistException, EmailExistException;
  
    //User added by super admin role
  	User addNewUser(Long parentUserId, String username, String email, String phoneNumber, String addressLine1,
  			List<Shop> shopList, Role role, String remarks)  throws UserNotFoundException, UsernameExistException, EmailExistException;

  	List<User> getUsers();

    User findUserByUsername(String username);

    User findUserByEmail(String email);
    
    
}
