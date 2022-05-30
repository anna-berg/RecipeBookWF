package com.berg.integration.dao;

import com.berg.entity.User;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static com.berg.entity.QUser.user;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    public List<User> findAll(EntityManager entityManager) {
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .fetch();
    }

    public List<User> findAllByName(EntityManager entityManager, String name) {
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(user.name.eq(name))
                .fetch();
    }


    public static UserDao getInstance() {
        return INSTANCE;
    }
}
