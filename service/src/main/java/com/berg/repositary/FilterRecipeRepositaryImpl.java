package com.berg.repositary;

import com.berg.dto.recipe.RecipeFilter;
import com.berg.entity.Recipe;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static com.berg.entity.QProduct.product;
import static com.berg.entity.QRecipe.recipe;

@RequiredArgsConstructor
public class FilterRecipeRepositaryImpl implements FilterRecipeRepositary{

    private final EntityManager entityManager;

    @Override
    public List<Recipe> findAllByFilter(RecipeFilter filter) {
        var predicate = QPredicate.builder()
                .add(filter.getAuthor(), recipe.author.name::containsIgnoreCase)
                .add(filter.getCategoryRecipe(), recipe.categoryRecipe.category::containsIgnoreCase)
                .buildAnd();
        return new JPAQuery<Recipe>(entityManager)
                .select(recipe)
                .from(recipe)
                .join(recipe.products, product)
                .where(predicate)
                .fetch();
//        return null;
    }
}
