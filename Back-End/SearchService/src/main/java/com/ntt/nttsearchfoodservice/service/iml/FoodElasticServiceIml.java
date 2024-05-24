package com.ntt.nttsearchfoodservice.service.iml;

import com.ntt.nttsearchfoodservice.dto.Food;
import com.ntt.nttsearchfoodservice.entity.FoodElasticEntity;
import com.ntt.nttsearchfoodservice.repository.FoodElasticRepo;
import com.ntt.nttsearchfoodservice.service.FoodElasticService;
import com.ntt.nttsearchfoodservice.service.FoodMongodbService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodElasticServiceIml implements FoodElasticService {

    @Autowired
    private FoodElasticRepo foodElasticRepo;
    @Autowired
    private FoodMongodbService foodMongodbService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Food> searchFood(String searchText) {
        return List.of();
    }

    @Override
    public Food insertFood(Food food) {
        return null;
    }

    @Override
    public Food deleteFood(Food food) {
        return null;
    }

    @Override
    public Food updateFood(Food food) {
        return null;
    }

    @Override
    public List<Food> insertAllFood() {
        foodElasticRepo.deleteAll();
        foodElasticRepo.saveAll(modelMapper.map(foodMongodbService.getAllFood(),new TypeToken<List<FoodElasticEntity>>(){}.getType()));
        return foodMongodbService.getAllFood();
    }
}
