package com.NTTT.PersonalInfoService.Command.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface PhysicCharsRepository extends JpaRepository<PhysicChars, Integer> {


  Optional<PhysicChars> findById(int Id);

  Optional<PhysicChars> findByPhysicCharsId(String physicCharsId);

}
