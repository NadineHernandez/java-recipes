package com.portfolio.recipe.models;

import java.util.Objects;

public class Ingredient {
    private int ingredientId;
    private String ingredientName;
    private String amount;
    private int recipeId;

    public Ingredient() {
    }

    public Ingredient(String ingredientName, String amount) {
        this.ingredientName = ingredientName;
        this.amount = amount;
    }

    public Ingredient(String ingredientName, String amount, int recipeId) {
        this.ingredientName = ingredientName;
        this.amount = amount;
        this.recipeId = recipeId;
    }

    public Ingredient(int ingredientId, String ingredientName, String amount, int recipeId) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.amount = amount;
        this.recipeId = recipeId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return ingredientId == that.ingredientId &&
                recipeId == that.recipeId &&
                ingredientName.equals(that.ingredientName) &&
                amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientId, ingredientName, amount, recipeId);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredientId=" + ingredientId +
                ", ingredientName='" + ingredientName + '\'' +
                ", amount='" + amount + '\'' +
                ", recipeId=" + recipeId +
                '}';
    }
}
