package com.ntt.nttmeal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ntt.nttmeal.entity.Meal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MacroResponse {
    private double calories;
    private double protein;
    private double fat;
    private double carbs;

    public MacroResponse(Meal mealRequest) {
        calories = mealRequest.getCalories();
        protein = mealRequest.getProtein();
        fat = mealRequest.getFat();
        carbs = mealRequest.getCarbs();
    }
}
