package com.portfolio.recipe.DAO;

import com.portfolio.recipe.models.Recipe;

import java.util.List;

public interface RecipeDAO {
    public Recipe addRecipe(Recipe recipe);
    public List<Recipe> getAllRecipes();
    public Recipe getRecipeById(int recipeId);
    public void updateRecipe(Recipe recipe);
    public void deleteRecipe(int recipeId);
    public List<Recipe> getRecipesByName(String recipeName);
    public List<Recipe> getRecipeByCategory(String category);
    public List<Recipe> getRecipeByTotalTime(int ttlTimeMins);
}
