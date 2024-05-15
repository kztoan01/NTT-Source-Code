package com.fakedata.fakedata.DTO;

import lombok.Data;
import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;

@Data

public class Recipe {
    private int userId;
    private String name;
    private double calories;
    private double protein;
    private double fats;
    private double carbs;
    private double sugar;
    private double cholesterol;
    private String description;
    private int cookingTime;
    private List<String> step;
    private List<String> ingredient;
    private String image;

    public Recipe(List<Recipe> recipes) throws Exception{
        Faker faker = new Faker();
        setUserId(faker.number().numberBetween(1, 100));
        if(recipes.isEmpty()) setName(faker.food().vegetable());
        int random = faker.number().numberBetween(1,8);
        if (random == 1) name = faker.food().allergen();
        else if (random == 2) name = faker.food().dish();
        else if (random == 3) name = faker.food().fruit();
        else if (random == 4) name = faker.food().ingredient();
        else if (random == 5) name = faker.food().spice();
        else if (random == 6) name = faker.food().measurement();
        else if (random == 7) name = faker.food().sushi();
        else if (random == 8) name = faker.food().vegetable();
        for (Recipe food : recipes) {
            if(food.getName().equalsIgnoreCase(name))
                throw new Exception("dup");

        }
        setName(name);
        setCalories(faker.number().randomDouble(2,0,1000));
        setCarbs(faker.number().randomDouble(2,0,1000));
        setProtein(faker.number().randomDouble(2,0,1000));
        setFats(faker.number().randomDouble(2,0,1000));
        setSugar(faker.number().randomDouble(2,0,1000));
        setCholesterol(faker.number().randomDouble(2,0,1000));
        setDescription(faker.gameOfThrones().quote());
        setCookingTime(faker.number().numberBetween(30, 240));
        int n = faker.number().numberBetween(5,50);
        List<String> steps = new ArrayList<>();
        List<String> ingredient = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            steps.add(faker.familyGuy().quote());
        }
        setStep(steps);
        n = faker.number().numberBetween(5,50);
        for (int i = 0; i < n; i++) {
            ingredient.add(faker.simpsons().quote());
        }
        setIngredient(ingredient);
        setImage(faker.internet().url());
    }
}
