package com.fakedata.fakedata.service;

import com.fakedata.fakedata.DTO.Food;
import com.fakedata.fakedata.DTO.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeFoodService {

    public List<Food> generateFakeFood(int total) {
        List<Food> foods = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
            Food food;
            try {
                food = new Food(foods);
                foods.add(food);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return foods;
    }

    public List<Recipe> generateFakeRecipe(int total) {
        List<Recipe> foods = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
            Recipe food;
            try {
                food = new Recipe(foods);
                foods.add(food);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return foods;
    }
}
