package com.berg.service.dao;

import com.berg.service.entity.QFavoriteRecipe;
import com.berg.service.entity.QRecipe;
import com.berg.service.entity.QUser;
import com.berg.service.entity.Recipe;
import com.berg.service.entity.User;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

import static com.berg.service.entity.QFavoriteRecipe.favoriteRecipe;
import static com.berg.service.entity.QRecipe.recipe;
import static com.berg.service.entity.QUser.user;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    public List<User> findAll(Session session) {
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .fetch();
    }

    public List<User> findAllByName(Session session, String name) {
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .where(user.name.eq(name))
                .fetch();
    }


    public static UserDao getInstance() {
        return INSTANCE;
    }
}
