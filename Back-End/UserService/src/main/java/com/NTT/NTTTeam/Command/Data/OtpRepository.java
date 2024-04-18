package com.NTT.NTTTeam.Command.Data;

import com.NTT.NTTTeam.Command.Data.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp,Integer> {

    Otp findByUserId(Integer userId);

}
