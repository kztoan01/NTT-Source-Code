package com.ntt.nttsearchfoodservice.service;

import com.ntt.nttsearchfoodservice.dto.Food;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FoodElasticService {
    public List<Food> searchFood(String searchText);
    public Food insertFood(Food food);
    public Food deleteFood(Food food);
    public Food updateFood(Food food);
    public List<Food> insertAllFood();
}
