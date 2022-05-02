package com.berg.dao;

import com.berg.dto.RecipeFilter;
import com.berg.entity.Product;
import com.berg.entity.Recipe;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

import static com.berg.service.entity.QRecipe.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecipeDao {

    private static final RecipeDao INSTANCE = new RecipeDao();

    public List<Recipe> findAll(Session session) {
        return new JPAQuery<Recipe>(session)
                .select(recipe)
                .from(recipe)
                .fetch();
    }

    public List<Recipe> findRecipeByProduct(Session session, Product product) {
        return new JPAQuery<Recipe>(session)
                .select(recipe)
                .from(recipe)
                .where(recipe.products.contains(product))
                .fetch();
    }

    public List<Recipe> findRecipeByFilter(Session session, RecipeFilter filter) {
        var predicate = QPredicate.builder()
                .add(filter.getAuthor(), recipe.author.id::eq)
                .add(filter.getCategoryRecipe(), recipe.categoryRecipe.id::eq)
                .add(filter.getProducts(), recipe.products.any().id::in)
                .buildAnd();

        return new JPAQuery<Recipe>(session)
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