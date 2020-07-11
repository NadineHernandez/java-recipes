package com.portfolio.recipe.DAO;

import com.portfolio.recipe.models.Ingredient;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class IngredientJdbcImpl implements IngredientDAO{

    private static final String INSERT_INGREDIENT_SQL = "INSERT INTO ingredient (ingredientName, amount, recipeId) VALUE (?,?,?)";
    private static final String SELECT_INGREDIENT_SQL = "SELECT * FROM ingredient WHERE ingredientId = ?";
    private static final String SELECT_ALL_INGREDIENTS_SQL = "SELECT * FROM ingredient";
    private static final String UPDATE_INGREDIENT_SQL = "UPDATE ingredient SET ingredientName = ?, amount = ?, recipeId = ?" +
            " WHERE ingredientId = ?";
    private static final String DELETE_INGREDIENT_SQL = "DELETE FROM ingredient WHERE ingredientId = ?";
    private static final String SELECT_INGREDIENTS_BY_RECIPE_ID = "SELECT * FROM ingredient WHERE recipeId = ?";
    private static final String SELECT_INGREDIENTS_BY_NAME_SQL = "SELECT * FROM ingredient WHERE ingredientName LIKE ?";

    private JdbcTemplate jdbcTemplate;

    public IngredientJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        jdbcTemplate.update(INSERT_INGREDIENT_SQL, ingredient.getIngredientName(), ingredient.getAmount(), ingredient.getRecipeId());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        ingredient.setIngredientId(id);
        return ingredient;
    }

    @Override
    public Ingredient getIngredientById(int ingredientId) {
        try{
            return jdbcTemplate.queryForObject(SELECT_INGREDIENT_SQL, this::mapRowToIngredient, ingredientId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return jdbcTemplate.query(SELECT_ALL_INGREDIENTS_SQL, this::mapRowToIngredient);
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        jdbcTemplate.update(UPDATE_INGREDIENT_SQL, ingredient.getIngredientName(), ingredient.getAmount(),
                ingredient.getRecipeId(), ingredient.getIngredientId());
    }

    @Override
    public void deleteIngredient(int ingredientId) {
        jdbcTemplate.update(DELETE_INGREDIENT_SQL, ingredientId);
    }

    @Override
    public List<Ingredient> getIngredientsByRecipeId(int recipeId) {
        return jdbcTemplate.query(SELECT_INGREDIENTS_BY_RECIPE_ID, this::mapRowToIngredient, recipeId);
    }

    @Override
    public List<Ingredient> getIngredientsByName(String ingredientName) {
        ingredientName = "%" + ingredientName + "%";
        return jdbcTemplate.query(SELECT_INGREDIENTS_BY_NAME_SQL, this::mapRowToIngredient, ingredientName);
    }

    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientId(rs.getInt("ingredientId"));
        ingredient.setIngredientName(rs.getString("ingredientName"));
        ingredient.setAmount(rs.getString("amount"));
        ingredient.setRecipeId(rs.getInt("recipeId"));
        return ingredient;
    }
}
