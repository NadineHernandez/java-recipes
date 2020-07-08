package com.portfolio.recipe.DAO;

import com.portfolio.recipe.models.Recipe;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RecipeJdbcImpl implements RecipeDAO {

    private static final String INSERT_RECIPE_SQL = "INSERT INTO recipe (recipeName, recipeImage, category, instructions," +
            " cookTimeMins, prepTimeMins, totalTimeMins) VALUE (?,?,?,?,?,?,?)";
    private static final String SELECT_ALL_RECIPES_SQL = "SELECT * FROM recipe";
    private static final String SELECT_RECIPE_SQL = "SELECT * FROM recipe WHERE recipeId = ?";
    private static final String UPDATE_RECIPE_SQL = "UPDATE recipe SET recipeName = ? , recipeImage = ? , category = ? ," +
            " instructions = ? , cookTimeMins = ? , prepTimeMins = ? , totalTimeMins = ? WHERE recipeId = ?";
    private static final String DELETE_RECIPE_SQL = "DELETE FROM recipe WHERE recipeId = ?";
    private static final String SELECT_RECIPES_BY_NAME = "SELECT * FROM recipe WHERE recipeName LIKE ?";
    private static final String SELECT_RECIPES_BY_CATEGORY = "SELECT * FROM recipe WHERE category = ?";
    private static final String SELECT_RECIPES_BY_TOTAL_TIME = "SELECT * FROM recipe WHERE totalTimeMins <= ?";

    private JdbcTemplate jdbcTemplate;

    public RecipeJdbcImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        jdbcTemplate.update(INSERT_RECIPE_SQL, recipe.getRecipeName(), recipe.getRecipeImage(), recipe.getCategory(),
                 recipe.getInstructions(), recipe.getCookTimeMins(), recipe.getPrepTimeMins(), recipe.getTotalTimeMins());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        recipe.setRecipeId(id);
        return recipe;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return jdbcTemplate.query(SELECT_ALL_RECIPES_SQL, this::mapRowToRecipe);
    }

    @Override
    public Recipe getRecipeById(int recipeId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_RECIPE_SQL, this::mapRowToRecipe, recipeId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        jdbcTemplate.update(UPDATE_RECIPE_SQL, recipe.getRecipeName(), recipe.getRecipeImage(), recipe.getCategory(),
                 recipe.getInstructions(), recipe.getCookTimeMins(), recipe.getPrepTimeMins(), recipe.getTotalTimeMins(),
                 recipe.getRecipeId());
    }

    @Override
    public void deleteRecipe(int recipeId) {
        jdbcTemplate.update(DELETE_RECIPE_SQL, recipeId);
    }

    @Override
    public List<Recipe> getRecipesByName(String recipeName) {
        recipeName = "%" + recipeName + "%";
        return jdbcTemplate.query(SELECT_RECIPES_BY_NAME, this::mapRowToRecipe, recipeName);
    }

    @Override
    public List<Recipe> getRecipeByCategory(String category) {
        return jdbcTemplate.query(SELECT_RECIPES_BY_CATEGORY, this::mapRowToRecipe, category);
    }

    @Override
    public List<Recipe> getRecipeByTotalTime(int ttlTimeMins) {
        return jdbcTemplate.query(SELECT_RECIPES_BY_TOTAL_TIME, this::mapRowToRecipe, ttlTimeMins);
    }

    private Recipe mapRowToRecipe(ResultSet rs, int rowNum) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(rs.getInt("recipeId"));
        recipe.setRecipeName(rs.getString("recipeName"));
        recipe.setRecipeImage(rs.getString("recipeImage"));
        recipe.setCategory(rs.getString("category"));
        recipe.setInstructions(rs.getString("instructions"));
        recipe.setCookTimeMins(rs.getInt("cookTimeMins"));
        recipe.setPrepTimeMins(rs.getInt("prepTimeMins"));
        recipe.setTotalTimeMins(rs.getInt("totalTimeMins"));
        return recipe;
    }
}
