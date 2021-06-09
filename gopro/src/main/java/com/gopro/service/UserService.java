package com.gopro.service;

import com.gopro.bene.Role;
import com.gopro.bene.SearchCredentialDTO;
import com.gopro.bene.Shop;
import com.gopro.bene.User;
import com.gopro.exception.domain.EmailExistException;
import com.gopro.exception.domain.UserNotFoundException;
import com.gopro.exception.domain.UsernameExistException;

import java.util.List;

import org.springframework.data.domain.Page;

public interface UserService {

    User register(String userName,String shopName, String email, String password) throws UserNotFoundException, UsernameExistException, EmailExistException;
  
    //User added by super admin role
  	User addNewUser(Long parentUserId, String firstName, String email, String phoneNumber, String addressLine1,
  			List<Shop> shopList, Role role, String remarks)  throws UserNotFoundException, UsernameExistException, EmailExistException;

  	List<User> getUsers();

    User findUserByUsername(String username);
    
	User findUserById(Long id) throws UserNotFoundException;

    User findUserByEmail(String email);

	Page<User> getAllUserPaginationAndSorting(SearchCredentialDTO searchCredentialDTO);


	User updateUser(Long id, String firstName, String phoneNumber, String addressLine1, List<Shop> shopList,
			Role roleObject, String remarks)  throws UserNotFoundException;
    
    
}
