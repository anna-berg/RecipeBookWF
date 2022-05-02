package com.berg.dao;

import com.berg.entity.Author;

import javax.persistence.EntityManager;

public class AuthorRepository extends RepositoryBase<Long, Author> {

    public AuthorRepository(EntityManager entityManager) {
        super(Author.class, entityManager);
    }
}
