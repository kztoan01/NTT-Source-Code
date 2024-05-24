package com.ntt.nttmeal.service.iml;

import com.ntt.nttmeal.dto.MealRequest;
import com.ntt.nttmeal.entity.Meal;
import com.ntt.nttmeal.exception.NotFoundDataException;
import com.ntt.nttmeal.repository.MealRepo;
import com.ntt.nttmeal.service.MealService;
import com.ntt.nttmeal.service.SequenceGeneratorService;
import com.ntt.nttmeal.util.DayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class MealServiceIml implements MealService {
    @Autowired
    private MealRepo mealRepository;
    @Autowired
    private SequenceGeneratorService sequenceGenerator;

    @Override
    public List<Meal> getMeals(int userId) {
        return mealRepository.findByUserId(userId);
    }

//    @Override
//    public Meal getMealById(int mealId) {
//        return mealRepository.findById(mealId).get();
//    }

    @Override
    public Meal addMeal(MealRequest meal) {
        Meal m = new Meal(meal,sequenceGenerator.getSequenceNumber(Meal.SEQUENCE_NAME));
        return mealRepository.save(m);
    }

    @Override
    public void updateMeal(Meal meal) {
        if(!mealRepository.existsById(meal.getId())) {
            throw new NotFoundDataException("Data Meal Not Found");
        }
        mealRepository.save(meal);
    }

    @Override
    public void deleteMeal(int mealId) {
        mealRepository.deleteById(mealId);
    }

    @Override
    public List<Meal> getMealsByDay(Instant day,int userId) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        Instant dateF = LocalDateTime.parse(DayUtil.changeTime(day,"00:00:00"), formatter).toInstant(ZoneOffset.UTC);
        Instant dateT = LocalDateTime.parse(DayUtil.changeTime(day,"23:59:00"), formatter).toInstant(ZoneOffset.UTC);
        return mealRepository.findByDate(dateF,dateT,userId);
    }

    @Override
    public List<Meal> getMealsByDay(Instant day,int dayBefore,int userId) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        Instant dateF = LocalDateTime.parse(DayUtil.changeTime(day.minus(dayBefore,ChronoUnit.DAYS),"00:00:00"), formatter).toInstant(ZoneOffset.UTC);
        Instant dateT = LocalDateTime.parse(DayUtil.changeTime(day,"23:59:59"), formatter).toInstant(ZoneOffset.UTC);
        return mealRepository.findByDate(dateF,dateT,userId);
    }
    @Override
    public List<Meal> getMealsByEachDay(Instant day,int dayBefore,int userId) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        Instant dateF = LocalDateTime.parse(DayUtil.changeTime(day.minus(dayBefore,ChronoUnit.DAYS),"00:00:00"), formatter).toInstant(ZoneOffset.UTC);
        Instant dateT = LocalDateTime.parse(DayUtil.changeTime(day.minus(dayBefore,ChronoUnit.DAYS),"23:59:59"), formatter).toInstant(ZoneOffset.UTC);
        return mealRepository.findByDate(dateF,dateT,userId);
    }

}
