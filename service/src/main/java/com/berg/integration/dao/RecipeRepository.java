package com.berg.integration.dao;

import com.berg.entity.Recipe;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class RecipeRepository extends RepositoryBase<Long, Recipe> {

    public RecipeRepository( EntityManager entityManager) {
        super(Recipe.class, entityManager);
    }
}
