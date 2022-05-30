package com.berg.integration.dao;

import com.berg.entity.CategoryRecipe;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CategoryRepository extends RepositoryBase<Long, CategoryRecipe> {

    public CategoryRepository(EntityManager entityManager) {
        super(CategoryRecipe.class, entityManager);
    }
}
