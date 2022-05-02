package com.berg.dao;

import com.berg.entity.Product;

import javax.persistence.EntityManager;

public class ProductRepository extends RepositoryBase<Long, Product> {

    public ProductRepository(EntityManager entityManager) {
        super(Product.class, entityManager);
    }
}
