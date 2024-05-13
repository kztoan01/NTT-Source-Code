package com.ntt.nttsearchfoodservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "food")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodElasticEntity {
    private int id;
    private String name;
}
