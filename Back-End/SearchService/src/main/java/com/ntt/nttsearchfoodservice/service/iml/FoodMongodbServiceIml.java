package com.ntt.nttsearchfoodservice.service.iml;

import com.ntt.nttsearchfoodservice.dto.Food;
import com.ntt.nttsearchfoodservice.entity.FoodMongodbEntity;
import com.ntt.nttsearchfoodservice.repository.FoodMongodbRepo;
import com.ntt.nttsearchfoodservice.service.FoodMongodbService;
import com.ntt.nttsearchfoodservice.service.SequenceGeneratorService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FoodMongodbServiceIml implements FoodMongodbService {

    @Autowired
    private FoodMongodbRepo foodMongodbRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;


    @Override
    public List<Food> getAllFood() {
        List<FoodMongodbEntity> foodMongodbEntityList = foodMongodbRepo.findAll();
        foodMongodbEntityList.sort(Comparator.comparing(FoodMongodbEntity::getName));
        return modelMapper.map(foodMongodbEntityList, new TypeToken<List<Food>>() {
        }.getType());
    }

    @Override
    public List<Food> getListFood(List<Food> foodList) {
        List<Food> result = new ArrayList<>();
        for (Food foods : foodList) {
            Food food = modelMapper.map(foodMongodbRepo.findById(foods.getId()), Food.class);
            result.add(food);
        }
        result.sort(Comparator.comparing(Food::getName));
        return result;
    }

    @Override
    public Food getFood(String foodName) {
        return null;
    }

    @Override
    public Food getFood(int id) {
        return modelMapper.map(foodMongodbRepo.findById(id), Food.class);
    }

    @Override
    public void addFood(Food food) {

        foodMongodbRepo.save(modelMapper.map(food, FoodMongodbEntity.class));
    }

    @Override
    public void updateFood(Food food) {

    }

    @Override
    public void deleteFood(Food food) {

    }

    @Override
    public void addListFood(List<Food> listFood) {
        for (Food food : listFood) {
            food.setId(sequenceGeneratorService.getSequenceNumber(FoodMongodbEntity.SEQUENCE_NAME));
        }
        foodMongodbRepo.saveAll(modelMapper.map(listFood, new TypeToken<List<FoodMongodbEntity>>() {
        }.getType()));
    }
}
