package com.ntt.nttsearchfoodservice.controller;

import com.ntt.nttsearchfoodservice.dto.Food;
import com.ntt.nttsearchfoodservice.dto.ListPaging;
import com.ntt.nttsearchfoodservice.service.ElasticService;
import com.ntt.nttsearchfoodservice.service.FilesStorageService;
import com.ntt.nttsearchfoodservice.service.FoodElasticService;
import com.ntt.nttsearchfoodservice.service.FoodMongodbService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodMongodbService foodMongodbService;
    @Autowired
    private ElasticService elasticService;
    @Autowired
    private FilesStorageService storageService;

    @GetMapping("/suggest/{searchValue}")
    public ResponseEntity<List<String>> suggestFood(@PathVariable String searchValue) throws IOException {
        List<Food> food = elasticService.autoSuggest(searchValue);
        List<String> listName = new ArrayList<>();
        for (Food food1 : food) {
            listName.add(food1.getName());
        }
        return ResponseEntity.ok(listName);
    }
    @GetMapping("/search/{searchValue}")
    public ResponseEntity<ListPaging<Food>> searchFood(@PathVariable String searchValue) throws IOException {
        ListPaging<Food> foodList = new ListPaging<>();
        foodList.setPageSize(10);
        foodList.setData(foodMongodbService.getListFood(elasticService.search(searchValue)));
        return ResponseEntity.ok(foodList);
    }

    @GetMapping("{id}")
    public ResponseEntity<Food> getFood(@PathVariable int id) {
        return ResponseEntity.ok(foodMongodbService.getFood(id));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Food>> getAllFood() {
        ListPaging<Food> foodList = new ListPaging<>();
        foodList.setPageSize(8);
        foodList.setData(foodMongodbService.getAllFood());
        return ResponseEntity.ok(new ArrayList<>());
    }


    @PostMapping("/upload")
    public ResponseEntity<String> excel(@RequestParam("file") MultipartFile file ) {
        String message = "";
        try {
            storageService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
}
