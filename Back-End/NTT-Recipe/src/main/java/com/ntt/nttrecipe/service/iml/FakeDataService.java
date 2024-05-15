package com.ntt.nttrecipe.service.iml;

import com.ntt.nttrecipe.entity.Recipe;
import com.ntt.nttrecipe.repository.FakeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeDataService {
    @Autowired
    FakeData fakeData;

    public List<Recipe> getFakeData(int total){
        return fakeData.getFakeData(total).getBody();
    }

}
