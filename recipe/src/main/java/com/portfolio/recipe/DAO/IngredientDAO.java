package com.portfolio.recipe.DAO;

import com.portfolio.recipe.models.Ingredient;

import java.util.List;

public interface IngredientDAO {
    public Ingredient addIngredient(Ingredient ingredient);
    public Ingredient getIngredientById(int ingredientId);
    public List<Ingredient> getAllIngredients();
    public void updateIngredient(Ingredient ingredient);
    public void deleteIngredient(int ingredientId);
    public List<Ingredient> getIngredientsByRecipeId(int recipeId);
    public List<Ingredient> getIngredientsByName(String ingredientName);
}
