package com.gopro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gopro.bene.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    User findUserByEmail(String email);
}
