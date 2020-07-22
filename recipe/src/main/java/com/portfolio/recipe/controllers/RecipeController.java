package com.portfolio.recipe.controllers;

import com.portfolio.recipe.models.RecipeViewModel;
import com.portfolio.recipe.service.ServiceLayer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private ServiceLayer serviceLayer;
    public RecipeController(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    @PostMapping(value = "/recipe")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRecipe(@RequestBody RecipeViewModel rvm) {

    }

    @GetMapping(value = "/recipe/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public RecipeViewModel getRecipe(@PathVariable int id) {
        return null;
    }

    //get all recipes
    @GetMapping(value = "/recipe")
    @ResponseStatus(HttpStatus.FOUND)
    public List<RecipeViewModel> getAllRecipes() {
        return null;
    }

    //delete recipe
    @DeleteMapping(value = "/recipe/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRecipe(@PathVariable int id) {

    }

    //update recipe
    @PutMapping(value = "/recipe")
    @ResponseStatus(HttpStatus.OK)
    public void updateRecipe(@RequestBody RecipeViewModel rvm) {

    }

    //get recipes by name
    @GetMapping(value = "/recipe/name/{name}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<RecipeViewModel> getRecipesByName(@PathVariable String name) {
        return null;
    }

    //get recipes by category
    @GetMapping(value = "/recipe/category/{category}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<RecipeViewModel> getRecipesByCategory(@PathVariable String category) {
        return null;
    }

    //get recipes by time
    @GetMapping(value = "/recipe/time/{time}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<RecipeViewModel> getRecipesByTime(@PathVariable String time) {
        return null;
    }

    //get recipes by ingredient
    @GetMapping(value = "/recipe/ingredient/{ingredient}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<RecipeViewModel> getRecipesByIngredient(@PathVariable String ingredient) {
        return null;
    }

}
