package com.portfolio.recipe.service;

import com.portfolio.recipe.DAO.IngredientDAO;
import com.portfolio.recipe.DAO.RecipeDAO;
import com.portfolio.recipe.models.Ingredient;
import com.portfolio.recipe.models.Recipe;
import com.portfolio.recipe.models.RecipeViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public RecipeViewModel buildRecipeViewModel(Recipe recipe, List<Ingredient> ingredients) {
        RecipeViewModel recipeViewModel = new RecipeViewModel();
        recipeViewModel.setRecipeId(recipe.getRecipeId());
        recipeViewModel.setRecipeName(recipe.getRecipeName());
        recipeViewModel.setRecipeImage(recipe.getRecipeImage());
        recipeViewModel.setCategory(recipe.getCategory());
        recipeViewModel.setInstructions(recipe.getInstructions());
        recipeViewModel.setIngredients(ingredients);
        recipeViewModel.setCookTimeMins(recipe.getCookTimeMins());
        recipeViewModel.setPrepTimeMins(recipe.getPrepTimeMins());
        recipeViewModel.setTotalTimeMins(recipe.getTotalTimeMins());
        return recipeViewModel;
    }

    @Transactional
    public RecipeViewModel saveRecipe(RecipeViewModel recipeViewModel){
        Recipe recipe = new Recipe();
        recipe.setRecipeName(recipeViewModel.getRecipeName());
        recipe.setRecipeImage(recipeViewModel.getRecipeImage());
        recipe.setCategory(recipeViewModel.getCategory());
        recipe.setInstructions(recipeViewModel.getInstructions());
        recipe.setCookTimeMins(recipeViewModel.getCookTimeMins());
        recipe.setPrepTimeMins(recipeViewModel.getPrepTimeMins());
        int totalTime = recipe.getCookTimeMins() + recipe.getPrepTimeMins();
        recipe.setTotalTimeMins(totalTime);
        Recipe recipeWithId = recipeDAO.addRecipe(recipe);

        List<Ingredient> ingredients = recipeViewModel.getIngredients();
        List<Ingredient> ingredientsWithIds = new ArrayList<>();
        ingredients.stream().forEach(ingredient -> {
            ingredient.setRecipeId(recipeWithId.getRecipeId());
            ingredientDAO.addIngredient(ingredient);
            ingredientsWithIds.add(ingredient);
        });

        recipeViewModel.setRecipeId(recipeWithId.getRecipeId());
        recipeViewModel.setIngredients(ingredientsWithIds);

        return recipeViewModel;
    }

    public RecipeViewModel findRecipeById(int id) {
        Recipe recipe = recipeDAO.getRecipeById(id);
        List<Ingredient> ingredients = ingredientDAO.getIngredientsByRecipeId(id);
        return buildRecipeViewModel(recipe, ingredients);
    }

    public List<RecipeViewModel> findAllRecipes(){
        List<Recipe> recipes = recipeDAO.getAllRecipes();
        List<RecipeViewModel> recipeViewModels = new ArrayList<>();
        recipes.stream().forEach(recipe -> {
            List<Ingredient> ingredients = ingredientDAO.getIngredientsByRecipeId(recipe.getRecipeId());
            recipeViewModels.add(buildRecipeViewModel(recipe, ingredients));
        });
        return recipeViewModels;
    }

    @Transactional
    public void removeRecipe(int id){
        recipeDAO.deleteRecipe(id);
        List<Ingredient> ingredients = ingredientDAO.getIngredientsByRecipeId(id);
        ingredients.stream().forEach(ingredient -> {
            ingredientDAO.deleteIngredient(ingredient.getIngredientId());
        });
    }

    @Transactional
    public void updateRecipe(RecipeViewModel recipeViewModel){
        Recipe recipe = new Recipe();
        recipe.setRecipeId(recipeViewModel.getRecipeId());
        recipe.setRecipeName(recipeViewModel.getRecipeName());
        recipe.setRecipeImage(recipeViewModel.getRecipeImage());
        recipe.setCategory(recipeViewModel.getCategory());
        recipe.setInstructions(recipeViewModel.getInstructions());
        recipe.setCookTimeMins(recipeViewModel.getCookTimeMins());
        recipe.setPrepTimeMins(recipeViewModel.getPrepTimeMins());
        int totalTime = recipe.getCookTimeMins() + recipe.getPrepTimeMins();
        recipe.setTotalTimeMins(totalTime);
        recipeDAO.updateRecipe(recipe);

        List<Ingredient> ingredients = recipeViewModel.getIngredients();
        ingredients.stream().forEach(ingredient -> {
            ingredient.setRecipeId(recipe.getRecipeId());
            ingredientDAO.updateIngredient(ingredient);
        });

    }

    public List<RecipeViewModel> findRecipesByName(String recipeName){
        List<Recipe> recipes = recipeDAO.getRecipesByName(recipeName);
        List<RecipeViewModel> recipeViewModels = new ArrayList<>();
        recipes.stream().forEach(recipe -> {
            List<Ingredient> ingredients = ingredientDAO.getIngredientsByRecipeId(recipe.getRecipeId());
            recipeViewModels.add(buildRecipeViewModel(recipe, ingredients));
        });
        return recipeViewModels;
    }

    public List<RecipeViewModel> findRecipesByCategory(String category){
        List<Recipe> recipes = recipeDAO.getRecipeByCategory(category);
        List<RecipeViewModel> recipeViewModels = new ArrayList<>();
        recipes.stream().forEach(recipe -> {
            List<Ingredient> ingredients = ingredientDAO.getIngredientsByRecipeId(recipe.getRecipeId());
            recipeViewModels.add(buildRecipeViewModel(recipe, ingredients));
        });
        return recipeViewModels;
    }

    public List<RecipeViewModel> findRecipesByTotalTime(int time){
        List<Recipe> recipes = recipeDAO.getRecipeByTotalTime(time);
        List<RecipeViewModel> recipeViewModels = new ArrayList<>();
        recipes.stream().forEach(recipe -> {
            List<Ingredient> ingredients = ingredientDAO.getIngredientsByRecipeId(recipe.getRecipeId());
            recipeViewModels.add(buildRecipeViewModel(recipe, ingredients));
        });
        return recipeViewModels;
    }

    public List<RecipeViewModel> findRecipesByIngredient(String ingredientName){
        List<Ingredient> ingredients = ingredientDAO.getIngredientsByName(ingredientName);
        List<Recipe> recipes = new ArrayList<>();
        ingredients.stream().forEach(ingredient -> {
            Recipe recipe = recipeDAO.getRecipeById(ingredient.getRecipeId());
            if(!recipes.contains(recipe)){
                recipes.add(recipe);
            }
        });
        List<RecipeViewModel> recipeViewModels = new ArrayList<>();
        recipes.stream().forEach(recipe -> {
            List<Ingredient> ingredientList = ingredientDAO.getIngredientsByRecipeId(recipe.getRecipeId());
            recipeViewModels.add(buildRecipeViewModel(recipe, ingredientList));
        });
        return recipeViewModels;
    }
}
