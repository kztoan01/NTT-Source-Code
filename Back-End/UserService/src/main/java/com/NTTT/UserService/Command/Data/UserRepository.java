package com.NTTT.UserService.Command.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.NTTT.UserService.Command.Data.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByUserName(String username);


  Optional<User> findByEmailAddress(String emailAddress);

  Optional<User> findByUserId(String userId);


  @Query("select count(p) = 1 from User p where userName = ?1")
  boolean existsUserByUserName(String username);

  @Query("select count(p) = 1 from User p where phoneNumber = ?1")
  boolean existUserByPhoneNumber(String phonenumber);

  @Query("select count(p) = 1 from User p where emailAddress = ?1")
  boolean existUserByEmailAddress(String emailAddress);

}
