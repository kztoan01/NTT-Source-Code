package com.NTTT.PersonalInfoService.Command.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserPlanningRepository extends JpaRepository<UserPlanning,Integer> {

    Optional<UserPlanning> findById(int Id);


    @Query("SELECT u FROM UserPlanning u WHERE u.userId = :id")
    List<UserPlanning> findByUserId(@Param("id") String id);
}
