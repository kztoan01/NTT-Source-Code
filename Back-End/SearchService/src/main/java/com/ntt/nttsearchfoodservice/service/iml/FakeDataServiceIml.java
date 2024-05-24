package com.ntt.nttsearchfoodservice.service.iml;

import com.ntt.nttsearchfoodservice.dto.Food;
import com.ntt.nttsearchfoodservice.repository.FakeData;
import com.ntt.nttsearchfoodservice.service.FakeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeDataServiceIml implements FakeDataService {
    @Autowired
    FakeData fakeData;

    @Override
    public List<Food> getFakeData(int total) {
        return fakeData.getFakeData(total).getBody();
    }
}
