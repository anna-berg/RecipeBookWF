package com.berg.dao;

import com.berg.service.dao.RecipeDao;
import com.berg.service.dao.UserDao;
import com.berg.service.dto.RecipeFilter;
import com.berg.service.entity.CategoryRecipe;
import com.berg.service.entity.Product;
import com.berg.service.entity.Recipe;
import com.berg.service.entity.User;
import com.berg.util.EntityHelper;
import com.berg.util.HibernateTestUtil;
import com.berg.util.TestDataImporter;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class RecipeDaoTest {

    private final SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
    private final RecipeDao recipeDao = RecipeDao.getInstance();

    @BeforeAll
    public void initDb() {
        TestDataImporter.importData(sessionFactory);
    }

    @AfterAll
    public void finish() {
        sessionFactory.close();
    }

    @Test
    void findAll() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Recipe> results = recipeDao.findAll(session);
        assertThat(results).hasSize(10);

        List<String> titles = results.stream().map(Recipe::getTitle).collect(toList());
        assertThat(titles).containsExactlyInAnyOrder("title1", "title2", "title3", "title4", "title5",
                "title6", "title7", "title8", "title9", "title10");

        var productsName = results.get(3).getProducts().stream()
                .map(Product::getName)
                .collect(toList());
        assertThat(productsName).hasSize(4);
        assertThat(productsName).containsExactlyInAnyOrder("buckwheat", "cabbage", "carrot", "cucumber");

        session.getTransaction().commit();
    }

    @Test
    void findRecipeByProductTest(){
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        var product = session.get(Product.class, 4L);
//        var product = EntityHelper.createProduct("cucumber", "vegetables");
        var recipeByProduct = recipeDao.findRecipeByProduct(session, product);
        assertThat(recipeByProduct).hasSize(7);

        session.getTransaction().commit();
    }

    @Test
    void findRecipeByFilter(){
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        var categoryRecipe = session.get(CategoryRecipe.class, 1L);
        var filter = RecipeFilter.builder()
                .categoryRecipe(categoryRecipe.getId())
                .build();

        var recipeByFilter = recipeDao.findRecipeByFilter(session, filter);
        assertThat(recipeByFilter).hasSize(2);

        session.getTransaction().commit();
    }


}
