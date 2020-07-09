package com.portfolio.recipe.service;

import com.portfolio.recipe.DAO.IngredientDAO;
import com.portfolio.recipe.DAO.RecipeDAO;
import com.portfolio.recipe.models.Ingredient;
import com.portfolio.recipe.models.Recipe;
import com.portfolio.recipe.models.RecipeViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ServiceLayer {
    private IngredientDAO ingredientDAO;
    private RecipeDAO recipeDAO;

    @Autowired
    public ServiceLayer(IngredientDAO ingredientDAO, RecipeDAO recipeDAO) {
        this.ingredientDAO = ingredientDAO;
        this.recipeDAO = recipeDAO;
    }

    private RecipeViewModel buildRecipeViewModel(Recipe recipe, Ingredient ingredient) {
        return null;
    }

    @Transactional
    private RecipeViewModel saveRecipe(RecipeViewModel recipeViewModel){
        return null;
    }

    public RecipeViewModel findRecipeById(int id) {
        return null;
    }

    public List<RecipeViewModel> findAllRecipes(){
        return null;
    }

    @Transactional
    public void removeRecipe(int id){

    }

    @Transactional
    public void updateRecipe(RecipeViewModel recipeViewModel){

    }

    public List<RecipeViewModel> findRecipesByName(String recipeName){
        return null;
    }

    public List<RecipeViewModel> findRecipesByCategory(String category){
        return null;
    }

    public List<RecipeViewModel> findRecipesByTotalTime(int time){
        return null;
    }

    public List<RecipeViewModel> findRecipesByIngredient(String ingredient){
        return null;
    }
}
