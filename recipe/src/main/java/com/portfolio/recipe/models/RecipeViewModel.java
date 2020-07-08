package com.portfolio.recipe.models;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RecipeViewModel {

    private String recipeName;
    private String recipeImage;
    private String category;
    private String instructions;
    private List<Ingredient> ingredients;
    private int cookTimeMins;
    private int prepTimeMins;
    private int totalTimeMins;

    public RecipeViewModel() {
    }

    public RecipeViewModel(String recipeName, String recipeImage, String category, String instructions,
                           List<Ingredient> ingredients, int cookTimeMins, int prepTimeMins, int totalTimeMins) {
        this.recipeName = recipeName;
        this.recipeImage = recipeImage;
        this.category = category;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.cookTimeMins = cookTimeMins;
        this.prepTimeMins = prepTimeMins;
        this.totalTimeMins = totalTimeMins;
    }

    public RecipeViewModel(String recipeImage, String category, String instructions, List<Ingredient> ingredients,
                           int cookTimeMins, int prepTimeMins, int totalTimeMins) {
        this.recipeImage = recipeImage;
        this.category = category;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.cookTimeMins = cookTimeMins;
        this.prepTimeMins = prepTimeMins;
        this.totalTimeMins = totalTimeMins;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getCookTimeMins() {
        return cookTimeMins;
    }

    public void setCookTimeMins(int cookTimeMins) {
        this.cookTimeMins = cookTimeMins;
    }

    public int getPrepTimeMins() {
        return prepTimeMins;
    }

    public void setPrepTimeMins(int prepTimeMins) {
        this.prepTimeMins = prepTimeMins;
    }

    public int getTotalTimeMins() {
        return totalTimeMins;
    }

    public void setTotalTimeMins(int totalTimeMins) {
        this.totalTimeMins = totalTimeMins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeViewModel that = (RecipeViewModel) o;
        return cookTimeMins == that.cookTimeMins &&
                prepTimeMins == that.prepTimeMins &&
                totalTimeMins == that.totalTimeMins &&
                recipeName.equals(that.recipeName) &&
                recipeImage.equals(that.recipeImage) &&
                category.equals(that.category) &&
                instructions.equals(that.instructions) &&
                ingredients.equals(that.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeName, recipeImage, category, instructions, ingredients, cookTimeMins, prepTimeMins, totalTimeMins);
    }

    @Override
    public String toString() {
        return "RecipeViewModel{" +
                "recipeName='" + recipeName + '\'' +
                ", recipeImage='" + recipeImage + '\'' +
                ", category='" + category + '\'' +
                ", instructions='" + instructions + '\'' +
                ", ingredients=" + ingredients +
                ", cookTimeMins=" + cookTimeMins +
                ", prepTimeMins=" + prepTimeMins +
                ", totalTimeMins=" + totalTimeMins +
                '}';
    }
}
