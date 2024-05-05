package com.NTTT.UserService.Command.Data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpEntityRepository extends JpaRepository<OtpEntity,Integer> {

    OtpEntity findByUserEmail(String userId);

}
