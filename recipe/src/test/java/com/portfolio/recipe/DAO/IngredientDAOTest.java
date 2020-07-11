package com.portfolio.recipe.DAO;

import com.portfolio.recipe.models.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class IngredientDAOTest {

    @Autowired
    IngredientDAO dao;

    @BeforeEach
    void setUp() {
        List<Ingredient> ingredients = dao.getAllIngredients();
        ingredients.stream()
                .forEach(ingredient -> dao.deleteIngredient(ingredient.getIngredientId()));
    }

    @Test
    void addIngredient() {
        Ingredient ingredient = new Ingredient("Flour", "1 cup", 1);
        ingredient = dao.addIngredient(ingredient);

        assertEquals(ingredient, dao.getIngredientById(ingredient.getIngredientId()));
    }

    @Test
    void getIngredientById() {
        Ingredient ingredient = new Ingredient("Flour", "1 cup", 1);
        ingredient = dao.addIngredient(ingredient);

        assertEquals(ingredient, dao.getIngredientById(ingredient.getIngredientId()));
    }

    @Test
    void getAllIngredients() {
        Ingredient ingredient = new Ingredient("Flour", "1 cup", 1);
        ingredient = dao.addIngredient(ingredient);

        Ingredient ingredient2 = new Ingredient("Large Eggs", "2", 1);
        ingredient2 = dao.addIngredient(ingredient2);

        assertEquals(2, dao.getAllIngredients().size());
    }

    @Test
    void updateIngredient() {
        Ingredient ingredient = new Ingredient("Flour", "1 cup", 1);
        ingredient = dao.addIngredient(ingredient);
        ingredient.setAmount("2 cups");
        dao.updateIngredient(ingredient);

        assertEquals(ingredient, dao.getIngredientById(ingredient.getIngredientId()));
    }

    @Test
    void deleteIngredient() {
        Ingredient ingredient = new Ingredient("Flour", "1 cup", 1);
        ingredient = dao.addIngredient(ingredient);
        dao.deleteIngredient(ingredient.getIngredientId());

        assertNull(dao.getIngredientById(ingredient.getIngredientId()));
    }

    @Test
    void getIngredientsByRecipeId() {
        Ingredient ingredient = new Ingredient("Flour", "1 cup", 1);
        ingredient = dao.addIngredient(ingredient);

        Ingredient ingredient2 = new Ingredient("Large Eggs", "2", 2);
        ingredient2 = dao.addIngredient(ingredient2);

        assertEquals(ingredient2, dao.getIngredientsByRecipeId(2).get(0));
        assertEquals(1, dao.getIngredientsByRecipeId(2).size());
    }

    @Test
    void getIngredientsByName() {
        Ingredient ingredient = new Ingredient("Flour", "1 cup", 1);
        ingredient = dao.addIngredient(ingredient);

        Ingredient ingredient2 = new Ingredient("Large Eggs", "2", 2);
        ingredient2 = dao.addIngredient(ingredient2);

        assertEquals(ingredient2, dao.getIngredientsByName(ingredient2.getIngredientName()).get(0));
        assertEquals(1, dao.getIngredientsByName(ingredient2.getIngredientName()).size());
    }
}