package com.ntt.nttsearchfoodservice.service.iml;

import com.ntt.nttsearchfoodservice.dto.Food;
import com.ntt.nttsearchfoodservice.exception.UploadException;
import com.ntt.nttsearchfoodservice.service.FilesStorageService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
    private final Path root = Paths.get("src/main/resources/static");

    @Override
    public void init() throws IOException {

            Files.createDirectories(root);

    }

    @Override
    public void save(MultipartFile file) throws UploadException, IOException {
        deleteAll();
        Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }


    @Override
    public List<Food> toFoodList(String fileName) throws IOException{
        Workbook workbook = new XSSFWorkbook(new FileInputStream(root.resolve(fileName).toFile()));
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        List<Food> foodList = new ArrayList<>();
        boolean flag = false;
        for (Row row : sheet) {
            Food food = new Food();
            if (!flag) {
                flag = true;
                continue;
            }
            food.setName(row.getCell(0).toString());
            food.setCalories(row.getCell(1).getNumericCellValue());
            food.setProtein(row.getCell(2).getNumericCellValue());
            food.setFat(row.getCell(3).getNumericCellValue());
            food.setCarbs(row.getCell(4).getNumericCellValue());
            food.setDescription(row.getCell(5).toString());
            food.setImage(row.getCell(6).toString());
            foodList.add(food);
        }
        workbook.close();
        deleteAll();
        return foodList;
    }
}
