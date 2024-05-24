package com.ntt.nttsearchfoodservice.service;

import com.ntt.nttsearchfoodservice.entity.Recipe;

import java.util.List;

public interface RecipeService {
    void addRecipe(Recipe recipe);

    void deleteRecipe(Recipe recipe);

    void updateRecipe(Recipe recipe);

    Recipe getRecipe(int id);

    List<Recipe> getRecipes();

    List<Recipe> getRecipesByUserId(int userId);

    long deleteRecipeByUserId(int userId);
}
