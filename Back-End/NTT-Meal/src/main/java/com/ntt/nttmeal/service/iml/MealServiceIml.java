package com.ntt.nttmeal.service.iml;

import com.ntt.nttmeal.dto.MealRequest;
import com.ntt.nttmeal.entity.Meal;
import com.ntt.nttmeal.repository.MealRepo;
import com.ntt.nttmeal.service.MealService;
import com.ntt.nttmeal.service.SequenceGeneratorService;
import com.ntt.nttmeal.util.DayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;

@Service
public class MealServiceIml implements MealService {
    @Autowired
    private MealRepo mealRepository;
    @Autowired
    private SequenceGeneratorService sequenceGenerator;

    @Override
    public List<Meal> getMeals() {
        return mealRepository.findAll();
    }

    @Override
    public Meal getMealById(int mealId) {
        return mealRepository.findById(mealId).get();
    }

    @Override
    public Meal addMeal(MealRequest meal) throws ParseException {
        Meal m = new Meal(meal,sequenceGenerator.getSequenceNumber(Meal.SEQUENCE_NAME));
        return mealRepository.save(m);
    }

    @Override
    public Meal updateMeal(Meal meal) {
        return null;
    }

    @Override
    public void deleteMeal(int mealId) {
        mealRepository.deleteById(mealId);
    }

    @Override
    public List<Meal> getMealsByDay(Instant day) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        Instant dateF = LocalDateTime.parse(DayUtil.changeTime(day,"00:00:00"), formatter).toInstant(ZoneOffset.UTC);
        Instant dateT = LocalDateTime.parse(DayUtil.changeTime(day,"23:59:00"), formatter).toInstant(ZoneOffset.UTC);
        return mealRepository.findByDate(dateF,dateT);
    }

    @Override
    public List<Meal> getMealsByDay(Instant day,int month) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        int dayTotal = month*30;
        Instant dateF = LocalDateTime.parse(DayUtil.changeTime(day.minus(dayTotal,ChronoUnit.DAYS),"00:00:00"), formatter).toInstant(ZoneOffset.UTC);
        Instant dateT = LocalDateTime.parse(DayUtil.changeTime(day,"23:59:59"), formatter).toInstant(ZoneOffset.UTC);
        return mealRepository.findByDate(dateF,dateT);
    }

}
