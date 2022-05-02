package com.berg.dao;

import com.berg.entity.FavoriteRecipe;

import javax.persistence.EntityManager;

public class FavoriteRecipeRepository extends RepositoryBase<Long, FavoriteRecipe>{

    public FavoriteRecipeRepository(EntityManager entityManager) {
        super(FavoriteRecipe.class, entityManager);
    }
}
