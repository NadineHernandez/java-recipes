package com.portfolio.recipe.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.recipe.DAO.IngredientDAO;
import com.portfolio.recipe.DAO.RecipeDAO;
import com.portfolio.recipe.models.Ingredient;
import com.portfolio.recipe.models.Recipe;
import com.portfolio.recipe.models.RecipeViewModel;
import com.portfolio.recipe.service.ServiceLayer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@RunWith(SpringRunner.class)
@WebMvcTest(RecipeController.class)
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeDAO recipeDAO;

    @MockBean
    private IngredientDAO ingredientDAO;

    @MockBean
    private ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void addRecipe() throws Exception{
        Ingredient inputIngredient = new Ingredient("Flour", "1 cup", 1);
        List<Ingredient> inputIngredients = new ArrayList<>();
        inputIngredients.add(inputIngredient);
        RecipeViewModel inputRvm = new RecipeViewModel("Cake", "Image", "Dessert",
                "bake for 45min at 350", inputIngredients, 60, 15, 75);

        String inputJson = mapper.writeValueAsString(inputRvm);

        Ingredient outputIngredient = new Ingredient(1,"Flour", "1 cup", 1);
        List<Ingredient> outputIngredients = new ArrayList<>();
        outputIngredients.add(outputIngredient);
        RecipeViewModel outputRvm = new RecipeViewModel(1,"Cake", "Image", "Dessert",
                "bake for 45min at 350", outputIngredients, 60, 15, 75);

        String outputJson = mapper.writeValueAsString(outputRvm);

        when(serviceLayer.saveRecipe(inputRvm)).thenReturn(outputRvm);

        this.mockMvc.perform(post("/recipes/recipe")
        .content(inputJson)
        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getRecipe() throws Exception{
        Ingredient outputIngredient = new Ingredient(1,"Flour", "1 cup", 1);
        List<Ingredient> outputIngredients = new ArrayList<>();
        outputIngredients.add(outputIngredient);
        RecipeViewModel outputRvm = new RecipeViewModel(1,"Cake", "Image", "Dessert",
                "bake for 45min at 350", outputIngredients, 60, 15, 75);

        String outputJson = mapper.writeValueAsString(outputRvm);

        when(serviceLayer.findRecipeById(1)).thenReturn(outputRvm);

        this.mockMvc.perform((get("/recipes/recipe/1").with(csrf().asHeader())))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getAllRecipes() throws Exception{
        Ingredient outputIngredient = new Ingredient(1,"Flour", "1 cup", 1);
        List<Ingredient> outputIngredients = new ArrayList<>();
        outputIngredients.add(outputIngredient);
        RecipeViewModel outputRvm = new RecipeViewModel(1,"Cake", "Image", "Dessert",
                "bake for 45min at 350", outputIngredients, 60, 15, 75);

        Ingredient outputIngredient2 = new Ingredient(2,"eggs", "2", 2);
        List<Ingredient> outputIngredients2 = new ArrayList<>();
        outputIngredients.add(outputIngredient2);
        RecipeViewModel outputRvm2 = new RecipeViewModel(2,"Pie", "Image", "Dessert",
                "bake for 45min at 350", outputIngredients, 60, 15, 75);

        List<RecipeViewModel> rvms = new ArrayList<>();
        rvms.add(outputRvm);
        rvms.add(outputRvm2);

        when(serviceLayer.findAllRecipes()).thenReturn(rvms);

        List<RecipeViewModel> listChecker = new ArrayList<>();
        listChecker.addAll(rvms);

        String outputJson = mapper.writeValueAsString(listChecker);

        this.mockMvc.perform((get("/recipes/recipe").with(csrf().asHeader())))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().json(outputJson));
    }

    @Test
    void deleteRecipe() {
    }

    @Test
    void updateRecipe() {
    }

    @Test
    void getRecipesByName() {
    }

    @Test
    void getRecipesByCategory() {
    }

    @Test
    void getRecipesByTime() {
    }

    @Test
    void getRecipesByIngredient() {
    }
}