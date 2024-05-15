package com.ntt.nttrecipe.repository;

import com.ntt.nttrecipe.entity.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RecipeRepo extends MongoRepository<Recipe, Integer> {
    @Query("{'userId' : ?0}")
    List<Recipe> findByUserId(Integer userId);
    @Query(delete = true,value = "{'userId': ?0}")
    long deleteByUserId(Integer userId);
}
