package com.ntt.nttrecipe.controller;

import com.ntt.nttrecipe.entity.Recipe;
import com.ntt.nttrecipe.service.RecipeService;
import com.ntt.nttrecipe.service.iml.FakeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fake")
public class FakeDataController {
    @Autowired
    private FakeDataService fakeDataService;
    @Autowired
    private RecipeService recipeService;

    @PostMapping("/{total}")
    public ResponseEntity<String> getFakeData(@PathVariable int total) {
        List<Recipe> recipes = fakeDataService.getFakeData(total);
        for (Recipe recipe : recipes) {
            recipeService.addRecipe(recipe);
        }
        return ResponseEntity.ok("success");
    }
}
