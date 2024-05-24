package com.ntt.nttmeal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ntt.nttmeal.dto.MealRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
@Document
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meal {
    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";

    @Id
    private int id;
    private int userId;
    private String name;
    private double calories;
    private double protein;
    private double fat;
    private double carbs;
    private String note;
    private Instant createTime;

    public Meal(MealRequest mealRequest,int id) {
        this.userId = mealRequest.getUserId();
        this.name = mealRequest.getName();
        this.calories = mealRequest.getCalories();
        this.protein = mealRequest.getProtein();
        this.fat = mealRequest.getFat();
        this.carbs = mealRequest.getCarbs();
        this.note = mealRequest.getNote();
        this.createTime = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        this.id = id;
    }
}
