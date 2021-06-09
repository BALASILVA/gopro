package com.gopro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gopro.bene.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);
    
    User findUserByEmail(String email);

    @Query(value="SELECT * FROM   USER usr WHERE  Upper(usr.firstname) = Upper(Ifnull(:firstname, usr.firstname)) AND (usr.phonenumber) = (Ifnull(:phonenumber, usr.phonenumber)) AND Upper(usr.email) = Upper(Ifnull(:email, usr.email)) AND Upper(usr.roleid) = Upper(Ifnull(:roleid, usr.roleid)) AND Concat_ws(usr.parentuserid, usr.email, usr.firstname, usr.phonenumber, usr.roleid) LIKE :searchKeyWord AND usr.parentuserid = :parentuserid AND usr.id <> :parentuserid AND usr.id <> :loginuserid", nativeQuery=true)
	Page<User> getAllUserPaginationAndSorting(@Param("loginuserid") Long id,@Param("parentuserid")  Long parentUserId, @Param("firstname") String firstName, @Param("email")  String email,
			@Param("phonenumber")String phoneNumber,@Param("roleid") Long roleId, String searchKeyWord, Pageable pageable);

	

}
