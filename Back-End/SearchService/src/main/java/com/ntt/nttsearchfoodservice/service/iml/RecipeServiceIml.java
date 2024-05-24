package com.ntt.nttsearchfoodservice.service.iml;

import com.ntt.nttsearchfoodservice.entity.Recipe;
import com.ntt.nttsearchfoodservice.repository.RecipeRepo;
import com.ntt.nttsearchfoodservice.service.RecipeService;
import com.ntt.nttsearchfoodservice.service.SequenceGeneratorService;
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
    public void addRecipe(Recipe recipe) {
        recipe.setId(sequenceGeneratorService.getSequenceNumber(Recipe.SEQUENCE_NAME));
        recipeRepo.save(recipe);
    }

    @Override
    public void deleteRecipe(Recipe recipe) {
        recipeRepo.delete(recipe);
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        recipeRepo.save(recipe);
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
