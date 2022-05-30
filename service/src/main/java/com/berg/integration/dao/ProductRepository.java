package com.berg.integration.dao;

import com.berg.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ProductRepository extends RepositoryBase<Long, Product> {

    public ProductRepository(EntityManager entityManager) {
        super(Product.class, entityManager);
    }
}
