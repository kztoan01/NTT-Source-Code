package com.ntt.nttsearchfoodservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Food {
    private int id;
    private String name;
    private double calories;
    private double protein;
    private double fat;
    private double carbs;
    private String description;
    private String image;
}
