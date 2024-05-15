package com.ntt.nttrecipe.repository;

import com.ntt.nttrecipe.entity.Recipe;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
@FeignClient(name = "FakeData", url = "http://localhost:8081", path = "/api/food/fake")
public interface FakeData {
    @GetMapping("/recipe/{id}")
    ResponseEntity<List<Recipe>> getFakeData(@PathVariable("id") int id);
}
