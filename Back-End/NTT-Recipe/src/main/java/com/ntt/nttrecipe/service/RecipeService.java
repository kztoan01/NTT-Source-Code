package com.ntt.nttrecipe.service;

import com.ntt.nttrecipe.entity.Recipe;

import java.util.List;

public interface RecipeService {
    boolean addRecipe(Recipe recipe);
    boolean deleteRecipe(Recipe recipe);
    boolean updateRecipe(Recipe recipe);
    Recipe getRecipe(int id);
    List<Recipe> getRecipes();
    List<Recipe> getRecipesByUserId(int userId);
    long deleteRecipeByUserId(int userId);
}
