package com.berg.integration.dao;

import com.berg.entity.FavoriteRecipe;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class FavoriteRecipeRepository extends RepositoryBase<Long, FavoriteRecipe>{

    public FavoriteRecipeRepository(EntityManager entityManager) {
        super(FavoriteRecipe.class, entityManager);
    }
}
