package com.ntt.nttsearchfoodservice.controller;

import com.ntt.nttsearchfoodservice.repository.FoodMongodbRepo;
import com.ntt.nttsearchfoodservice.service.FilesStorageService;
import com.ntt.nttsearchfoodservice.service.FoodMongodbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/food/excel")
public class ExcelController {

    @Autowired
    FilesStorageService storageService;
    @Autowired
    FoodMongodbService foodMongodbService;
    @PostMapping("/upload")
    public ResponseEntity<String> excel(@RequestParam("file") MultipartFile file ) throws IOException {
        String message = "";

            storageService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            foodMongodbService.addListFood(storageService.toFoodList(file.getOriginalFilename()));
            return ResponseEntity.status(HttpStatus.OK).body(message);
    }

}
