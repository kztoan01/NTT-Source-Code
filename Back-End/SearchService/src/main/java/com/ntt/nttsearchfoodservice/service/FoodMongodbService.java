package com.ntt.nttsearchfoodservice.service;

import com.ntt.nttsearchfoodservice.dto.Food;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FoodMongodbService {
    List<Food> getAllFood();

    List<Food> getListFood(List<Food> foodList);

    Food getFood(String foodName);

    Food getFood(int id);

    void addFood(Food food);

    void updateFood(Food food);

    void deleteFood(Food food);

    void addListFood(List<Food> listFood);
}
