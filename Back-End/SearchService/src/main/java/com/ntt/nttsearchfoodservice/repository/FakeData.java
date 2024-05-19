package com.ntt.nttsearchfoodservice.repository;

import com.ntt.nttsearchfoodservice.dto.Food;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
@FeignClient(name = "FakeData", url = "${LOCAL_HOST}", path = "/api/food/fake")
public interface FakeData {
    @GetMapping("/{id}")
    ResponseEntity<List<Food>> getFakeData(@PathVariable("id") int id);
}
