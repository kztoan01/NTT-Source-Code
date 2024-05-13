package com.fakedata.fakedata.DTO;

import lombok.Data;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Data

public class Food {


    private String name;
    private double calories;
    private double protein;
    private double fat;
    private double carbs;
    private String description;
    private String image;

    public Food(List<Food> foodList) throws Exception{

        Faker faker = new Faker(new Locale("vi"));
        if(foodList.size() == 0) setName(faker.food().vegetable());
        int random = faker.number().numberBetween(1,8);
        if (random == 1) name = faker.food().allergen();
        else if (random == 2) name = faker.food().dish();
        else if (random == 3) name = faker.food().fruit();
        else if (random == 4) name = faker.food().ingredient();
        else if (random == 5) name = faker.food().spice();
        else if (random == 6) name = faker.food().measurement();
        else if (random == 7) name = faker.food().sushi();
        else if (random == 8) name = faker.food().vegetable();
        for (Food food : foodList) {
            if(food.getName().equalsIgnoreCase(name))
                throw new Exception("dup");

        }
        setName(name);
        setCalories(faker.number().randomDouble(2,0,1000));
        setProtein(faker.number().randomDouble(2,0,1000));
        setFat(faker.number().randomDouble(2,0,1000));
        setCarbs(faker.number().randomDouble(2,0,1000));
        setDescription(faker.babylon5().quote());
        setImage(faker.internet().url());
    }
}
