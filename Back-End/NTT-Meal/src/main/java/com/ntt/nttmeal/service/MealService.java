package com.ntt.nttmeal.service;

import com.ntt.nttmeal.dto.MealRequest;
import com.ntt.nttmeal.entity.Meal;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public interface MealService {
    List<Meal> getMeals();
    Meal getMealById(int mealId);
    Meal addMeal(MealRequest meal) throws ParseException;
    Meal updateMeal(Meal meal);
    void deleteMeal(int mealId);
    List<Meal> getMealsByDay(Instant day);
    List<Meal> getMealsByDay(Instant day,int month);
}
