package com.NTTT.UserService.Command.Data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.NTTT.UserService.Command.Data.Otp;

public interface OtpRepository extends JpaRepository<Otp,Integer> {

    Otp findByUserId(Integer userId);

}
