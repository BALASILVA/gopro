package com.gopro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gopro.bene.UserNewMailMapping;

public interface UserNewMailMappingRepo extends JpaRepository<UserNewMailMapping, Long> {

	@Query(value="UPDATE usernewmailmapping set hasNewMail = :value where userId IN ( :userId )", nativeQuery = true)
	int updateNewMessage(@Param("value") boolean value,@Param("userId") List<Long> userId);

}
