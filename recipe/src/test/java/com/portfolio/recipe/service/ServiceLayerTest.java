package com.portfolio.recipe.service;

import com.portfolio.recipe.DAO.IngredientDAO;
import com.portfolio.recipe.DAO.IngredientJdbcImpl;
import com.portfolio.recipe.DAO.RecipeDAO;
import com.portfolio.recipe.DAO.RecipeJdbcImpl;
import com.portfolio.recipe.models.Ingredient;
import com.portfolio.recipe.models.Recipe;
import com.portfolio.recipe.models.RecipeViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ServiceLayerTest {

    private ServiceLayer serviceLayer;
    private RecipeDAO recipeDAO;
    private IngredientDAO ingredientDAO;

    private void setUpRecipeDaoMock(){
        recipeDAO = mock(RecipeJdbcImpl.class);

        Recipe recipe = new Recipe("Cake", "Image", "Dessert",
                "bake for 45min at 350", 60, 15, 75);
        Recipe recipeWithId = new Recipe(1,"Cake", "Image", "Dessert",
                "bake for 45min at 350", 60, 15, 75);

        Recipe recipe2 = new Recipe("Pie", "Image", "Dessert",
                "bake for 30min at 350", 55, 20, 75);
        Recipe recipe2WithId = new Recipe(2,"Pie", "Image", "Dessert",
                "bake for 30min at 350", 55, 20, 75);

        List<Recipe> allRecipes = new ArrayList<>();
        allRecipes.add(recipeWithId);
        allRecipes.add(recipe2WithId);

        List<Recipe> nameRecipe = new ArrayList<>();
        nameRecipe.add(recipeWithId);

        doReturn(recipeWithId).when(recipeDAO).addRecipe(recipe);
        doReturn(recipeWithId).when(recipeDAO).getRecipeById(1);
        doReturn(recipe2WithId).when(recipeDAO).addRecipe(recipe2);
        doReturn(recipe2WithId).when(recipeDAO).getRecipeById(2);

        doReturn(allRecipes).when(recipeDAO).getAllRecipes();
        doReturn(allRecipes).when(recipeDAO).getRecipeByCategory("Dessert");
        doReturn(nameRecipe).when(recipeDAO).getRecipesByName("Cake");
        doReturn(allRecipes).when(recipeDAO).getRecipeByTotalTime(80);
    }

    private void setUpIngredientDaoMock(){
        ingredientDAO = mock(IngredientJdbcImpl.class);

        Ingredient ingredient = new Ingredient("Flour", "1 cup", 1);
        Ingredient ingredientWithId = new Ingredient(1,"Flour", "1 cup", 1);

        Ingredient ingredient2 = new Ingredient("Large Eggs", "2", 2);
        Ingredient ingredient2WithId = new Ingredient(2,"Large Eggs", "2", 2);

        List<Ingredient> allIngredients = new ArrayList<>();
        allIngredients.add(ingredientWithId);
        allIngredients.add(ingredient2WithId);

        List<Ingredient> recipeIngredients = new ArrayList<>();
        recipeIngredients.add(ingredientWithId);

        List<Ingredient> recipe2Ingredients = new ArrayList<>();
        recipeIngredients.add(ingredient2WithId);

        doReturn(ingredientWithId).when(ingredientDAO).addIngredient(ingredient);
        doReturn(ingredientWithId).when(ingredientDAO).getIngredientById(1);
        doReturn(ingredient2WithId).when(ingredientDAO).addIngredient(ingredient2);
        doReturn(ingredient2WithId).when(ingredientDAO).getIngredientById(2);

        doReturn(allIngredients).when(ingredientDAO).getAllIngredients();
        doReturn(recipeIngredients).when(ingredientDAO).getIngredientsByRecipeId(1);
        doReturn(recipe2Ingredients).when(ingredientDAO).getIngredientsByRecipeId(2);
    }

    @BeforeEach
    void setUp() {
        setUpRecipeDaoMock();
        setUpIngredientDaoMock();
        serviceLayer = new ServiceLayer(ingredientDAO, recipeDAO);
    }

    @Test
    void buildRecipeViewModel(){
        Ingredient ingredient = new Ingredient(1,"Flour", "1 cup", 1);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);

        Recipe recipeWithId = new Recipe(1,"Cake", "Image", "Dessert",
                "bake for 45min at 350", 60, 15, 75);

        RecipeViewModel recipeViewModel = new RecipeViewModel(1,"Cake", "Image", "Dessert",
                "bake for 45min at 350", ingredients, 60, 15, 75);

        assertEquals(recipeViewModel, serviceLayer.buildRecipeViewModel(recipeWithId, ingredients));
    }

    @Test
    void findRecipeById() {
        Ingredient ingredient = new Ingredient(1,"Flour", "1 cup", 1);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);

        Recipe recipeWithId = new Recipe(1,"Cake", "Image", "Dessert",
                "bake for 45min at 350", 60, 15, 75);

        RecipeViewModel recipeViewModel = new RecipeViewModel(1,"Cake", "Image", "Dessert",
                "bake for 45min at 350", ingredients, 60, 15, 75);

        assertEquals(recipeViewModel, serviceLayer.findRecipeById(1));
    }

    @Test
    void findAllRecipes() {
        Ingredient ingredient = new Ingredient(1,"Flour", "1 cup", 1);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);

        Recipe recipeWithId = new Recipe(1,"Cake", "Image", "Dessert",
                "bake for 45min at 350", 60, 15, 75);

        RecipeViewModel recipeViewModel = new RecipeViewModel(1,"Cake", "Image", "Dessert",
                "bake for 45min at 350", ingredients, 60, 15, 75);

        Ingredient ingredient2WithId = new Ingredient(2,"Large Eggs", "2", 2);
        List<Ingredient> ingredients2 = new ArrayList<>();
        ingredients2.add(ingredient2WithId);

        Recipe recipe2WithId = new Recipe(2,"Pie", "Image", "Dessert",
                "bake for 30min at 350", 55, 20, 75);

        RecipeViewModel recipeViewModel2 = new RecipeViewModel(2,"Pie", "Image", "Dessert",
                "bake for 30min at 350", ingredients2, 55, 20, 75);

        List<RecipeViewModel> recipeViewModels = new ArrayList<>();
        recipeViewModels.add(recipeViewModel);
        recipeViewModels.add(recipeViewModel2);

        assertEquals(recipeViewModels, serviceLayer.findAllRecipes());
    }

    @Test
    void removeRecipe() {
        Ingredient ingredient = new Ingredient(1,"Flour", "1 cup", 1);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);

        Recipe recipeWithId = new Recipe(1,"Cake", "Image", "Dessert",
                "bake for 45min at 350", 60, 15, 75);

        RecipeViewModel recipeViewModel = new RecipeViewModel(1,"Cake", "Image", "Dessert",
                "bake for 45min at 350", ingredients, 60, 15, 75);

        ArgumentCaptor<Recipe> recipeCaptor = ArgumentCaptor.forClass(Recipe.class);
        ArgumentCaptor<Ingredient> ingredientCaptor = ArgumentCaptor.forClass(Ingredient.class);
        serviceLayer.updateRecipe(recipeViewModel);

        verify(recipeDAO, times(1)).updateRecipe(recipeCaptor.capture());
        verify(ingredientDAO, times(1)).updateIngredient(ingredientCaptor.capture());
    }

    @Test
    void updateRecipe() {
        ArgumentCaptor<Integer> idCaptor = ArgumentCaptor.forClass(Integer.class);
        serviceLayer.removeRecipe(1);

        verify(recipeDAO, times(1)).deleteRecipe(idCaptor.capture());
        assertEquals(1, idCaptor.getValue().intValue());

        verify(ingredientDAO, times(1)).deleteIngredient(idCaptor.capture());
        assertEquals(1, idCaptor.getValue().intValue());
    }

    @Test
    void findRecipesByName() {
        Ingredient ingredient = new Ingredient(1,"Flour", "1 cup", 1);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);

        Recipe recipeWithId = new Recipe(1,"Cake", "Image", "Dessert",
                "bake for 45min at 350", 60, 15, 75);

        RecipeViewModel recipeViewModel = new RecipeViewModel(1,"Cake", "Image", "Dessert",
                "bake for 45min at 350", ingredients, 60, 15, 75);

        List<RecipeViewModel> recipeViewModels = new ArrayList<>();
        recipeViewModels.add(recipeViewModel);

        assertEquals(recipeViewModels, serviceLayer.findRecipesByName("Cake"));
    }

    @Test
    void findRecipesByCategory() {
        Ingredient ingredient = new Ingredient(1,"Flour", "1 cup", 1);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);

        Recipe recipeWithId = new Recipe(1,"Cake", "Image", "Dessert",
                "bake for 45min at 350", 60, 15, 75);

        RecipeViewModel recipeViewModel = new RecipeViewModel(1,"Cake", "Image", "Dessert",
                "bake for 45min at 350", ingredients, 60, 15, 75);

        Ingredient ingredient2WithId = new Ingredient(2,"Large Eggs", "2", 2);
        List<Ingredient> ingredients2 = new ArrayList<>();
        ingredients2.add(ingredient2WithId);

        Recipe recipe2WithId = new Recipe(2,"Pie", "Image", "Dessert",
                "bake for 30min at 350", 55, 20, 75);

        RecipeViewModel recipeViewModel2 = new RecipeViewModel(2,"Pie", "Image", "Dessert",
                "bake for 30min at 350", ingredients2, 55, 20, 75);

        List<RecipeViewModel> recipeViewModels = new ArrayList<>();
        recipeViewModels.add(recipeViewModel);
        recipeViewModels.add(recipeViewModel2);

        assertEquals(recipeViewModels, serviceLayer.findRecipesByCategory("Dessert"));
    }

    @Test
    void findRecipesByTotalTime() {
        Ingredient ingredient = new Ingredient(1,"Flour", "1 cup", 1);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);

        Recipe recipeWithId = new Recipe(1,"Cake", "Image", "Dessert",
                "bake for 45min at 350", 60, 15, 75);

        RecipeViewModel recipeViewModel = new RecipeViewModel(1,"Cake", "Image", "Dessert",
                "bake for 45min at 350", ingredients, 60, 15, 75);

        Ingredient ingredient2WithId = new Ingredient(2,"Large Eggs", "2", 2);
        List<Ingredient> ingredients2 = new ArrayList<>();
        ingredients2.add(ingredient2WithId);

        Recipe recipe2WithId = new Recipe(2,"Pie", "Image", "Dessert",
                "bake for 30min at 350", 55, 20, 75);

        RecipeViewModel recipeViewModel2 = new RecipeViewModel(2,"Pie", "Image", "Dessert",
                "bake for 30min at 350", ingredients2, 55, 20, 75);

        List<RecipeViewModel> recipeViewModels = new ArrayList<>();
        recipeViewModels.add(recipeViewModel);
        recipeViewModels.add(recipeViewModel2);

        assertEquals(recipeViewModels, serviceLayer.findRecipesByTotalTime(80));
    }

    @Test
    void findRecipesByIngredient() {
        Ingredient ingredient = new Ingredient(1,"Flour", "1 cup", 1);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);

        Recipe recipeWithId = new Recipe(1,"Cake", "Image", "Dessert",
                "bake for 45min at 350", 60, 15, 75);

        RecipeViewModel recipeViewModel = new RecipeViewModel(1,"Cake", "Image", "Dessert",
                "bake for 45min at 350", ingredients, 60, 15, 75);

        List<RecipeViewModel> recipeViewModels = new ArrayList<>();
        recipeViewModels.add(recipeViewModel);

        assertEquals(recipeViewModels, serviceLayer.findRecipesByIngredient("Flour"));
    }
}