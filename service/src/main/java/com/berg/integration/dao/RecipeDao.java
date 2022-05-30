package com.berg.integration.dao;

import com.berg.dto.RecipeFilter;
import com.berg.entity.Product;
import com.berg.entity.Recipe;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static com.berg.entity.QRecipe.recipe;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecipeDao {

    private static final RecipeDao INSTANCE = new RecipeDao();

    public List<Recipe> findAll(EntityManager entityManager) {
        return new JPAQuery<Recipe>(entityManager)
                .select(recipe)
                .from(recipe)
                .fetch();
    }

    public List<Recipe> findRecipeByProduct(EntityManager entityManager, Product product) {
        return new JPAQuery<Recipe>(entityManager)
                .select(recipe)
                .from(recipe)
                .where(recipe.products.contains(product))
                .fetch();
    }

    public List<Recipe> findRecipeByFilter(EntityManager entityManager, RecipeFilter filter) {
        var predicate = QPredicate.builder()
                .add(filter.getAuthor(), recipe.author.id::eq)
                .add(filter.getCategoryRecipe(), recipe.categoryRecipe.id::eq)
                .add(filter.getProducts(), recipe.products.any().id::in)
                .buildAnd();

        return new JPAQuery<Recipe>(entityManager)
                .select(recipe)
                .from(recipe)
//                .join(recipe.categoryRecipe, categoryRecipe)
                .where(predicate)
                .fetch();
    }

    public static RecipeDao getInstance() {
        return INSTANCE;
    }
}
