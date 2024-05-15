package com.ntt.nttrecipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Recipe {
    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";

    @Id
    private int id;
    private int userId;
    private String name;
    private double calories;
    private double protein;
    private double fats;
    private double carbs;
    private double sugar;
    private double cholesterol;
    private String description;
    private int cookingTime;
    private List<String> step;
    private List<String> ingredient;
    private String image;
}
