package com.ntt.nttsearchfoodservice.service;

import com.ntt.nttsearchfoodservice.dto.Food;
import com.ntt.nttsearchfoodservice.exception.UploadException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface FilesStorageService {
     void init() throws IOException;

     void save(MultipartFile file) throws IOException;


     void deleteAll();


    List<Food> toFoodList(String fileName) throws IOException;
}
