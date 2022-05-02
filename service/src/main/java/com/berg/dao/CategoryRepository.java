package com.berg.dao;

import com.berg.entity.CategoryRecipe;

import javax.persistence.EntityManager;

public class CategoryRepository extends RepositoryBase<Long, CategoryRecipe> {

    public CategoryRepository(EntityManager entityManager) {
        super(CategoryRecipe.class, entityManager);
    }
}
