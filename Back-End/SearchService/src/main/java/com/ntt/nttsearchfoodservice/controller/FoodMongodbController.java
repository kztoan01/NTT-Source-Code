package com.ntt.nttsearchfoodservice.controller;

import com.ntt.nttsearchfoodservice.dto.Food;
import com.ntt.nttsearchfoodservice.service.FoodMongodbService;
import com.ntt.nttsearchfoodservice.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping(value = "/mongo")
public class FoodMongodbController {
    @Autowired
    private FoodMongodbService foodMongodbService;

    @PostMapping
    public ResponseEntity<Food> addFood(@RequestBody Food food) {
        foodMongodbService.addFood(food);
        return ResponseEntity.ok(food);
    }

    @PostMapping("/listfood")
    public ResponseEntity<String> addFoodList(@RequestBody List<Food> foodList) {
        foodMongodbService.addListFood(foodList);
        return ResponseEntity.ok("Add Food List Successfully");
    }

    @GetMapping
    public ResponseEntity<List<Food>> getFoodList() {
        return ResponseEntity.ok(foodMongodbService.getAllFood());
    }


}
