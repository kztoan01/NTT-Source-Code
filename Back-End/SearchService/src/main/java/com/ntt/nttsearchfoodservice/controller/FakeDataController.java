package com.ntt.nttsearchfoodservice.controller;

import com.ntt.nttsearchfoodservice.dto.Food;
import com.ntt.nttsearchfoodservice.service.FakeDataService;
import com.ntt.nttsearchfoodservice.service.FoodMongodbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fakedata")
public class FakeDataController {
    @Autowired
    private FakeDataService fakeDataService;
    @Autowired
    private FoodMongodbService foodMongodbService;

    @PostMapping("/{total}")
    public String fakeData(@PathVariable("total") int total) {
        List<Food> foods = fakeDataService.getFakeData(total);
        foodMongodbService.addListFood(foods);
        return "success";
    }
}
