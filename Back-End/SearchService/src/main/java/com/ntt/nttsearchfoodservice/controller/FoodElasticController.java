package com.ntt.nttsearchfoodservice.controller;

import com.ntt.nttsearchfoodservice.dto.Food;
import com.ntt.nttsearchfoodservice.service.FoodElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

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
