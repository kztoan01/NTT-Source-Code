package com.ntt.nttmeal.service;

import com.ntt.nttmeal.dto.MealRequest;
import com.ntt.nttmeal.entity.Meal;

import java.time.Instant;
import java.util.List;

public interface MealService {
    List<Meal> getMeals(int userId);
//    Meal getMealById(int mealId);
    Meal addMeal(MealRequest meal);
    void updateMeal(Meal meal);
    void deleteMeal(int mealId);
    List<Meal> getMealsByDay(Instant day,int userId);
    List<Meal> getMealsByDay(Instant day,int month,int userId);
    List<Meal> getMealsByEachDay(Instant day,int month,int userId);

}
