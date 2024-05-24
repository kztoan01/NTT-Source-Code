package com.ntt.nttsearchfoodservice.controller;

import com.ntt.nttsearchfoodservice.entity.Recipe;
import com.ntt.nttsearchfoodservice.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getRecipes());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id) {
        return ResponseEntity.ok(recipeService.getRecipe(id));
    }

    @PostMapping
    public ResponseEntity<String> createRecipe(@RequestBody Recipe recipe) {
        recipeService.addRecipe(recipe);
        return ResponseEntity.ok("Successfully created recipe");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRecipe(@RequestBody Recipe recipe) {
        recipeService.deleteRecipe(recipe);
        return ResponseEntity.ok("Successfully deleted recipe");
    }

    @PutMapping
    public ResponseEntity<String> updateRecipe(@RequestBody Recipe recipe) {
        recipeService.updateRecipe(recipe);
        return ResponseEntity.ok("Successfully updated recipe");
    }

    @GetMapping("/userid/{id}")
    public ResponseEntity<List<Recipe>> getRecipeByUserId(@PathVariable int id) {
        return ResponseEntity.ok(recipeService.getRecipesByUserId(id));
    }

    @DeleteMapping("/userid/{id}")
    public ResponseEntity<String> deleteRecipeByUserId(@PathVariable int id) {
        long n = recipeService.deleteRecipeByUserId(id);
        return ResponseEntity.ok("Successfully deleted " + n + " recipe");
    }

}
