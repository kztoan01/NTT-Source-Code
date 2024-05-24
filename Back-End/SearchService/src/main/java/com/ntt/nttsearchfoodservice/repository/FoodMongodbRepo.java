package com.ntt.nttsearchfoodservice.repository;

import com.ntt.nttsearchfoodservice.entity.FoodMongodbEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodMongodbRepo extends MongoRepository<FoodMongodbEntity, Integer> {
}
