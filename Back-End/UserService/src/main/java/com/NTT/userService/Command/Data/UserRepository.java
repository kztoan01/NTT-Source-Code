package com.NTT.userService.Command.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.NTT.userService.Command.Data.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Integer> {

  User findByUserName(String username);


  User findByEmailAddress(String emailAddress);

  User findByUserId(String userId);


  @Query("select count(p) = 1 from User p where userName = ?1")
  boolean existsUserByUserName(String username);

  @Query("select count(p) = 1 from User p where phoneNumber = ?1")
  boolean existUserByPhoneNumber(String phonenumber);

  @Query("select count(p) = 1 from User p where emailAddress = ?1")
  boolean existUserByEmailAddress(String emailAddress);

}
