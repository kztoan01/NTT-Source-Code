package com.ntt.nttsearchfoodservice.repository;

import com.ntt.nttsearchfoodservice.entity.FoodElasticEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FoodElasticRepo extends ElasticsearchRepository<FoodElasticEntity, Integer> {

}
