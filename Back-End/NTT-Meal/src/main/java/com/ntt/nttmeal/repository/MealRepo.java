package com.ntt.nttmeal.repository;

import com.ntt.nttmeal.entity.Meal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface MealRepo extends MongoRepository<Meal, Integer> {
    @Query("{'createTime' : { $gte: ?0, $lte: ?1 },'userId' : ?2 }")
    List<Meal> findByDate(Instant dateFrom, Instant dateTo,int userId);
    List<Meal> findByUserId(Integer userId);
}
