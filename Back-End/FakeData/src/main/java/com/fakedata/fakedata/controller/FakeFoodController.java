package com.fakedata.fakedata.controller;

import com.fakedata.fakedata.DTO.Food;
import com.fakedata.fakedata.service.FakeFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/food/fake")
public class FakeFoodController {

    @Autowired
    private FakeFoodService fakeFoodService;

    @GetMapping("/{total}")
    public ResponseEntity<List<Food>> getFakeFood(@PathVariable int total) {
        return ResponseEntity.ok(fakeFoodService.generateFakeFood(total));
    }
}
