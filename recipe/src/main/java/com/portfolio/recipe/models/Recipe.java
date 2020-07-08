package com.portfolio.recipe.models;

import java.util.Map;
import java.util.Objects;

public class Recipe {
    private int recipeId;
    private String recipeName;
    private String recipeImage;
    private String category;
    private String instructions;
    private int cookTimeMins;
    private int prepTimeMins;
    private int totalTimeMins;

    public Recipe() {
    }

    public Recipe(String recipeName, String recipeImage, String category, String instructions, int cookTimeMins,
                  int prepTimeMins, int totalTimeMins) {
        this.recipeName = recipeName;
        this.recipeImage = recipeImage;
        this.category = category;
        this.instructions = instructions;
        this.cookTimeMins = cookTimeMins;
        this.prepTimeMins = prepTimeMins;
        this.totalTimeMins = totalTimeMins;
    }

    public Recipe(int recipeId, String recipeName, String recipeImage, String category, String instructions,
                  int cookTimeMins, int prepTimeMins, int totalTimeMins) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeImage = recipeImage;
        this.category = category;
        this.instructions = instructions;
        this.cookTimeMins = cookTimeMins;
        this.prepTimeMins = prepTimeMins;
        this.totalTimeMins = totalTimeMins;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
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
        Recipe recipe = (Recipe) o;
        return recipeId == recipe.recipeId &&
                cookTimeMins == recipe.cookTimeMins &&
                prepTimeMins == recipe.prepTimeMins &&
                totalTimeMins == recipe.totalTimeMins &&
                recipeName.equals(recipe.recipeName) &&
                recipeImage.equals(recipe.recipeImage) &&
                category.equals(recipe.category) &&
                instructions.equals(recipe.instructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, recipeName, recipeImage, category, instructions, cookTimeMins,
                prepTimeMins, totalTimeMins);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", recipeName='" + recipeName + '\'' +
                ", recipeImage='" + recipeImage + '\'' +
                ", category='" + category + '\'' +
                ", instructions='" + instructions + '\'' +
                ", cookTimeMins=" + cookTimeMins +
                ", prepTimeMins=" + prepTimeMins +
                ", totalTimeMins=" + totalTimeMins +
                '}';
    }
}
