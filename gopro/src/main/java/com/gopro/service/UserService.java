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

    User register(String userName,Shop shop, String email,String phone, String phoneNumber,String password, Long otp) throws UserNotFoundException, UsernameExistException, EmailExistException;
  
    //User added by super admin role
  	User addNewUser( String firstName,String lastName, String email, String phoneNumber, String addressLine1,
  			List<Shop> shopList, Role role, String remarks)  throws UserNotFoundException, UsernameExistException, EmailExistException, Exception;

  	List<User> getUsers();

    User findUserByUsername(String username);
    
	User findUserById(Long id) throws UserNotFoundException;

    User findUserByEmail(String email);

	Page<User> getAllUserPaginationAndSorting(SearchCredentialDTO searchCredentialDTO) throws Exception;


	User updateUser(Long id, String firstName, String phoneNumber, String addressLine1, List<Shop> shopList,
			Role roleObject, String remarks)  throws UserNotFoundException, Exception;

	boolean updateDefaultShop(Shop shop);

	List<User> getUserForReprintNotification(User logedInUser);
	
	List<User> getAdminANDManagerUser(Long parentUserID);
	
	List<User> getUserForSendMail();

	boolean haveNewMail();

	boolean updateNewMailTrue(List<Long> userId);

	boolean updateNewMailFalse(Long userId);

	boolean updateProfileImageUrl(Long id, String imageName);

	boolean changePassword(Long userId, String newPassword);

	boolean deleteUser(User userFromDB);

	boolean isMailAvailable(String email) ;

	User findUserByUsernameForUserId(String username);
    
}
