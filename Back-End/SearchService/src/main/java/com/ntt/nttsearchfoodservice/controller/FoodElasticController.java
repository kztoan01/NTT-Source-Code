package com.ntt.nttsearchfoodservice.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.ntt.nttsearchfoodservice.dto.Food;
import com.ntt.nttsearchfoodservice.service.FoodElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/food/elastic")
public class FoodElasticController {
    @Autowired
    private FoodElasticService foodElasticService;
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @PostMapping("/refresh")
    private ResponseEntity<List<Food>> updateFoodElastic() {
        return ResponseEntity.ok(foodElasticService.insertAllFood());
    }

    @GetMapping("/search/{value}")
    private ResponseEntity<List<Food>> searchFood(@PathVariable String value) {
        return ResponseEntity.ok(foodElasticService.searchFood(value));
    }
}
