package com.ntt.nttsearchfoodservice.service;

import com.ntt.nttsearchfoodservice.dto.Food;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FilesStorageService {
    void init() throws IOException;

    void save(MultipartFile file) throws IOException;


    void deleteAll();


    List<Food> toFoodList(String fileName) throws IOException;
}
