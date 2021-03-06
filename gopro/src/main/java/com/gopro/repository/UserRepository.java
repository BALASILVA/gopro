package com.gopro.repository;

import java.util.List;

import javax.persistence.Transient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.gopro.bene.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findUserByUsername(String username);

	User findUserByEmail(String email);

	@Query(value = "SELECT * FROM   USER usr WHERE  Upper(usr.firstname) = Upper(Ifnull(:firstname, usr.firstname)) AND (usr.phonenumber) = (Ifnull(:phonenumber, usr.phonenumber)) AND Upper(usr.email) = Upper(Ifnull(:email, usr.email)) AND Upper(usr.roleid) = Upper(Ifnull(:roleid, usr.roleid)) AND Concat_ws(usr.parentuserid, usr.email, usr.firstname, usr.phonenumber, usr.roleid) LIKE :searchKeyWord AND usr.roleid>:loginuserroleid AND usr.parentuserid = :parentuserid AND usr.id <> :parentuserid AND usr.id <> :loginuserid", nativeQuery = true)
	Page<User> getAllUserPaginationAndSorting(@Param("loginuserid") Long id, @Param("parentuserid") Long parentUserId,
			@Param("loginuserroleid") int logInUserRoleId, @Param("firstname") String firstName,
			@Param("email") String email, @Param("phonenumber") String phoneNumber, @Param("roleid") Long roleId,
			String searchKeyWord, Pageable pageable);

	@Modifying
	@Query(value = "update user set defaultshopid=:shopid where id=:userid", nativeQuery = true)
	void updateDefaultShopId(@Param("shopid") Long shopid, @Param("userid") Long userid);


	@Query(value = "select * from user usr where ( usr.parentUserId = :parentUserId OR usr.id = :parentUserId) AND usr.roleId in :roleId", nativeQuery = true)
	List<User> getUserByRole(@Param("parentUserId") Long parentUserId,
			@Param("roleId") List<Integer> roles);
 
	@Query(nativeQuery = true , name = "findUserForSendMail")
	List<User> getUserForSendMail(@Param("parentUserId") Long parentUserId);

	@Query(value = "select hasNewMail from user where userName = :logInUserName" , nativeQuery = true)
	boolean haveNewMail(@Param("logInUserName") String logInUserName);	
	
	@Modifying
	@Query(value = "update user set hasnewmail=:value where id in (:userId)", nativeQuery = true)
	void updateNewMailTrue(@Param("value") boolean value,@Param("userId")List<Long> userId );
	
	@Modifying
	@Query(value = "update user set hasnewmail=:value where id = :userId", nativeQuery = true)
	void updateNewMailFalse(@Param("value") boolean value,@Param("userId")Long userId );

	@Modifying
	@Query(value = "update user set profileimageurl=:imageName where id = :userId", nativeQuery = true)
	int updateProfileImageUrl(@Param("userId") Long id,@Param("imageName") String imageName);

	@Modifying
	@Query(value = "update user set password=:newPassword where id = :userId", nativeQuery = true)
	int updatePassword(@Param("userId")Long userId,@Param("newPassword") String newPassword);

	@Modifying
	@Query(value = "DELETE FROM user where id = :userId", nativeQuery = true)
	void deleteByIdCustom(@Param("userId")Long id);
	@Modifying
	@Query(value = "DELETE FROM user_shop_list where user_id = :userId", nativeQuery = true)	
	void deleteUserShopMaping(@Param("userId")Long id);

}
