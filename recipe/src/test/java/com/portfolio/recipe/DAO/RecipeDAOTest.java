package com.portfolio.recipe.DAO;

import com.portfolio.recipe.models.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class RecipeDAOTest {

    @Autowired
    RecipeDAO dao;

    @BeforeEach
    void setUp() {
        List<Recipe> recipes = dao.getAllRecipes();
        recipes.stream()
                .forEach(recipe -> dao.deleteRecipe(recipe.getRecipeId()));
    }

    @Test
    void addRecipe() {
        Recipe recipe = new Recipe("Cake", "Image", "Dessert",
                "bake for 45min at 350", 60, 15, 75);

        recipe = dao.addRecipe(recipe);

        assertEquals(recipe, dao.getRecipeById(recipe.getRecipeId()));
    }

    @Test
    void getAllRecipes() {
        Recipe recipe = new Recipe("Cake", "Image", "Dessert",
                "bake for 45min at 350", 60, 15, 75);
        dao.addRecipe(recipe);

        Recipe recipe2 = new Recipe("Pie", "Image", "Dessert",
                "bake for 30min at 350", 55, 20, 75);
        dao.addRecipe(recipe2);

        assertEquals(2, dao.getAllRecipes().size());
    }

    @Test
    void getRecipeById() {
        Recipe recipe = new Recipe("Cake", "Image", "Dessert",
                "bake for 45min at 350", 60, 15, 75);

        recipe = dao.addRecipe(recipe);

        assertEquals(recipe, dao.getRecipeById(recipe.getRecipeId()));
    }

    @Test
    void updateRecipe() {
        Recipe recipe = new Recipe("Cake", "Image", "Dessert",
                "bake for 45min at 350", 60, 15, 75);

        recipe = dao.addRecipe(recipe);
        recipe.setCategory("Side");
        dao.updateRecipe(recipe);

        assertEquals(recipe, dao.getRecipeById(recipe.getRecipeId()));
    }

    @Test
    void deleteRecipe() {
        Recipe recipe = new Recipe("Cake", "Image", "Dessert",
                "bake for 45min at 350", 60, 15, 75);

        recipe = dao.addRecipe(recipe);
        dao.deleteRecipe(recipe.getRecipeId());

        assertNull(dao.getRecipeById(recipe.getRecipeId()));
    }

    @Test
    void getRecipesByName() {
        Recipe recipe = new Recipe("Chocolate Cake", "Image", "Dessert",
                "bake for 45min at 350", 60, 15, 75);
        dao.addRecipe(recipe);

        Recipe recipe2 = new Recipe("Pie", "Image", "Dessert",
                "bake for 30min at 350", 55, 20, 75);
        dao.addRecipe(recipe2);

        assertEquals(recipe, dao.getRecipesByName("Cake").get(0));
        assertEquals(1, dao.getRecipesByName("Cake").size());
    }

    @Test
    void getRecipeByCategory() {
        Recipe recipe = new Recipe("Cake", "Image", "Dessert",
                "bake for 45min at 350", 60, 15, 75);
        dao.addRecipe(recipe);

        Recipe recipe2 = new Recipe("Omlette", "Image", "Entree",
                "cook in skillet", 5, 2, 7);
        dao.addRecipe(recipe2);

        assertEquals(recipe, dao.getRecipeByCategory("Dessert").get(0));
        assertEquals(1, dao.getRecipeByCategory("Dessert").size());
    }

    @Test
    void getRecipeByTotalTime() {
        Recipe recipe = new Recipe("Cake", "Image", "Dessert",
                "bake for 45min at 350", 60, 15, 75);
        dao.addRecipe(recipe);

        Recipe recipe2 = new Recipe("Omlette", "Image", "Entree",
                "cook in skillet", 5, 2, 7);
        dao.addRecipe(recipe2);

        assertEquals(recipe2, dao.getRecipeByTotalTime(10).get(0));
        assertEquals(1, dao.getRecipeByTotalTime(10).size());
    }
}