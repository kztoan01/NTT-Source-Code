package com.ntt.nttsearchfoodservice.controller;

import com.ntt.nttsearchfoodservice.service.FilesStorageService;
import com.ntt.nttsearchfoodservice.service.FoodMongodbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    FilesStorageService storageService;
    @Autowired
    FoodMongodbService foodMongodbService;

    @PostMapping("/upload")
    public ResponseEntity<String> excel(@RequestParam("file") MultipartFile file) throws IOException {
        String message = "";

        storageService.save(file);
        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        foodMongodbService.addListFood(storageService.toFoodList(file.getOriginalFilename()));
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

}
