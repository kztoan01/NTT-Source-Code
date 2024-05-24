package com.ntt.nttsearchfoodservice.controller;

import com.ntt.nttsearchfoodservice.dto.Food;
import com.ntt.nttsearchfoodservice.service.FoodElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/elastic")
public class FoodElasticController {
    @Autowired
    private FoodElasticService foodElasticService;

    @PostMapping("/refresh")
    private ResponseEntity<List<Food>> updateFoodElastic() {
        return ResponseEntity.ok(foodElasticService.insertAllFood());
    }

    @GetMapping("/")
    private ResponseEntity<String> homedocudo() {
        return ResponseEntity.ok("foodElastic");
    }
}
