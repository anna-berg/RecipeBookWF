package com.berg.integration.dao;

import com.berg.entity.Author;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class AuthorRepository extends RepositoryBase<Long, Author> {

    public AuthorRepository(EntityManager entityManager) {
        super(Author.class, entityManager);
    }
}
