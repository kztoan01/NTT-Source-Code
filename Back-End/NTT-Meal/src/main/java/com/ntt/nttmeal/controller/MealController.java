package com.ntt.nttmeal.controller;

import com.ntt.nttmeal.dto.MacroResponse;
import com.ntt.nttmeal.dto.MealRequest;
import com.ntt.nttmeal.entity.Meal;
import com.ntt.nttmeal.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAmount;
import java.util.List;

@RestController
@RequestMapping("/meal")
public class MealController {
    @Autowired
    private MealService mealService;

    @PostMapping
    public Meal addMeal(@RequestBody MealRequest meal) {
        return mealService.addMeal(meal);
    }
    @DeleteMapping
    public String deleteMeal(@RequestBody Meal meal) {
        mealService.deleteMeal(meal.getId());
        return "Delete meal successfully";
    }

    @PutMapping
    public String updateMeal(@RequestBody Meal meal) {
        mealService.updateMeal(meal);
        return "Update meal successfully";
    }

    @GetMapping
    public List<Meal> getAllMeals(@RequestBody MealRequest meal) {
        return mealService.getMeals(meal.getUserId());
    }

    @GetMapping("/today")
    public List<Meal> getMealToday(@RequestBody MealRequest meal) {
        Instant instant = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        return mealService.getMealsByDay(instant,meal.getUserId());
    }

    @GetMapping("/date/duration/{day}")
    public List<Meal> getMealFrom(@PathVariable int day,@RequestBody MealRequest mealRequest) {
        Instant instant = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        return mealService.getMealsByDay(instant,day,mealRequest.getUserId());
    }

    @GetMapping("/date/duration/")
    public List<Meal> getMealFrom(@RequestBody MealRequest mealRequest) {
        int month = 2;
        Instant instant = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        return mealService.getMealsByDay(instant,month,mealRequest.getUserId());
    }
    @GetMapping("macro/today")
    public MacroResponse getMacro(@RequestBody MealRequest mealRequest) {
        Instant instant = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        List<Meal> meals = mealService.getMealsByDay(instant,mealRequest.getUserId());
        Meal meal = new Meal();
        for (Meal meall : meals) {
            meal.setCalories(meal.getCalories() + meall.getCalories());
            meal.setFat(meal.getFat() + meall.getFat());
            meal.setCarbs(meal.getCarbs() + meall.getCarbs());
            meal.setProtein(meal.getProtein() + meall.getProtein());
        }
        return new MacroResponse(meal);
    }
    @GetMapping("macro/before/{day}")
    public MacroResponse getMacroBefore(@PathVariable int day,@RequestBody MealRequest mealRequest) {
        Instant instant = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        List<Meal> meals = mealService.getMealsByEachDay(instant,day,mealRequest.getUserId());
        Meal meal = new Meal();
        for (Meal meall : meals) {
            meal.setCalories(meal.getCalories()+meall.getCalories());
            meal.setFat(meal.getFat()+ meall.getFat());
            meal.setCarbs(meal.getCarbs()+meall.getCarbs());
            meal.setProtein(meal.getProtein()+ meall.getProtein());
        }
        return new MacroResponse(meal);
    }
}
