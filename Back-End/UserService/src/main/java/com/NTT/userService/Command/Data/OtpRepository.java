package com.NTT.userService.Command.Data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.NTT.userService.Command.Data.Otp;

public interface OtpRepository extends JpaRepository<Otp,Integer> {

    Otp findByUserId(Integer userId);

}
