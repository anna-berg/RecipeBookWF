package com.berg.dao;

import com.berg.entity.Recipe;

import javax.persistence.EntityManager;

public class RecipeRepository extends RepositoryBase<Long, Recipe> {

    public RecipeRepository( EntityManager entityManager) {
        super(Recipe.class, entityManager);
    }
}
