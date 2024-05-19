package com.ntt.nttmeal.controller;

import com.ntt.nttmeal.dto.MealRequest;
import com.ntt.nttmeal.entity.Meal;
import com.ntt.nttmeal.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RestController
@RequestMapping("/api/meal")
public class MealController {
    @Autowired
    private MealService mealService;

    @PostMapping
    public Meal addMeal(@RequestBody MealRequest meal) throws ParseException {
        return mealService.addMeal(meal);
    }

    @GetMapping
    public List<Meal> getAllMeals() {
        return mealService.getMeals();
    }

    @GetMapping("/today")
    public List<Meal> getMealToday() {
        Instant instant = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        return mealService.getMealsByDay(instant);
    }

    @GetMapping("/date/duration/{month}")
    public List<Meal> getMealFrom(@PathVariable int month) {
        Instant instant = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        return mealService.getMealsByDay(instant,month);
    }
}
