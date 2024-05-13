package com.ntt.nttsearchfoodservice.service;

import com.ntt.nttsearchfoodservice.dto.Food;

import java.util.List;

public interface FakeDataService {
    List<Food> getFakeData(int total);
}
