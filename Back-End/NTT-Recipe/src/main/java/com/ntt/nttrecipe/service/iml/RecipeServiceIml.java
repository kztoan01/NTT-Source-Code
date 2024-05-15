package com.ntt.nttrecipe.service.iml;

import com.ntt.nttrecipe.entity.Recipe;
import com.ntt.nttrecipe.repository.RecipeRepo;
import com.ntt.nttrecipe.service.RecipeService;

import com.ntt.nttrecipe.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceIml implements RecipeService {

    @Autowired
    private RecipeRepo recipeRepo;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;



    @Override
    public boolean addRecipe(Recipe recipe) {
        recipe.setId(sequenceGeneratorService.getSequenceNumber(Recipe.SEQUENCE_NAME));
        recipeRepo.save(recipe);
        return true;
    }

    @Override
    public boolean deleteRecipe(Recipe recipe) {
        recipeRepo.delete(recipe);
        return false;
    }

    @Override
    public boolean updateRecipe(Recipe recipe) {
        recipeRepo.save(recipe);
        return false;
    }

    @Override
    public Recipe getRecipe(int id) {
        return recipeRepo.findById(id).get();
    }

    @Override
    public List<Recipe> getRecipes() {
        return recipeRepo.findAll();
    }

    @Override
    public List<Recipe> getRecipesByUserId(int userId) {
        return recipeRepo.findByUserId(userId);
    }

    @Override
    public long deleteRecipeByUserId(int userId) {
        return recipeRepo.deleteByUserId(userId);
    }
}
