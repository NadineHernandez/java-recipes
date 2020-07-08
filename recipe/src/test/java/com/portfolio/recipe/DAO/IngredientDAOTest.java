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
    }

    @Test
    void getIngredientById() {
    }

    @Test
    void getAllIngredients() {
    }

    @Test
    void updateIngredient() {
    }

    @Test
    void deleteIngredient() {
    }

    @Test
    void getIngredientsByRecipeId() {
    }
}