package com.berg;

import com.berg.service.entity.Author;
import com.berg.service.entity.CategoryRecipe;
import com.berg.service.entity.DailyMenu;
import com.berg.service.entity.Recipe;
import com.berg.service.util.HibernateUtil;
import com.berg.util.EntityHelper;
import com.berg.util.HibernateTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class EntityMappingTest {

    @Test
    void SaveRecipeTest() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();


            session.getTransaction().commit();
        }
    }

    @Test
    void CreateRecipeTest() {
        var recipe = EntityHelper.createRecipe();

        try (var sessionFactory = HibernateTestUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {

            session.beginTransaction();

            session.save(recipe);
            session.evict(recipe);
            assertThat(recipe).isEqualTo(session.get(Recipe.class, recipe.getId()));
            session.getTransaction().commit();
        }
    }

    @Test
    void saveDailyMenu() {

        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            var breakfast = session.get(Recipe.class, 103L);
            var firstSnack = session.get(Recipe.class, 105L);
            var lunch = session.get(Recipe.class, 106L);
            var secondSnack = session.get(Recipe.class, 107L);
            var dinner = session.get(Recipe.class, 108L);

            var dailyMenu = DailyMenu.builder()
                    .breakfast(breakfast)
                    .firstSnack(firstSnack)
                    .lunch(lunch)
                    .secondSnack(secondSnack)
                    .dinner(dinner)
                    .title("day 6")
                    .build();
            session.save(dailyMenu);
            assertThat(session.get(DailyMenu.class, dailyMenu.getId())).isEqualTo(dailyMenu);
            session.getTransaction().commit();
        }
    }
}
