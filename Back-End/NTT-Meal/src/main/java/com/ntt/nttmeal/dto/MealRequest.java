package com.ntt.nttmeal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MealRequest {

    private int userId;
    private String name;
    private double calories;
    private double protein;
    private double fat;
    private double carbs;
    private String note;
}
