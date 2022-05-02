package com.berg.dao;

import com.berg.dto.RecipeFilter;
import com.berg.entity.Author;
import com.berg.entity.CategoryRecipe;
import com.berg.entity.Product;
import com.berg.entity.Recipe;
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
    void findRecipeByProductTest() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        var product = session.get(Product.class, 4L);
//        var product = EntityHelper.createProduct("cucumber", "vegetables");
        var recipeByProduct = recipeDao.findRecipeByProduct(session, product);
        assertThat(recipeByProduct).hasSize(7);

        session.getTransaction().commit();
    }

    @Test
    void findRecipeByFilterWithCategory() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        var categoryRecipe = session.get(CategoryRecipe.class, 1L);
        var filter = RecipeFilter.builder()
                .categoryRecipe(categoryRecipe.getId())
                .build();

        var recipeByFilter = recipeDao.findRecipeByFilter(session, filter);
        assertThat(recipeByFilter).hasSize(2);

        var recipeTitles = recipeByFilter.stream()
                .map(Recipe::getTitle)
                .collect(toList());
        assertThat(recipeTitles).containsExactlyInAnyOrder("title1", "title6");

        session.getTransaction().commit();
    }

    @Test
    void findRecipeByFilterWithAuthor() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        var author = session.get(Author.class, 2L);
        var filter = RecipeFilter.builder()
                .author(author.getId())
                .build();

        var recipeByFilter = recipeDao.findRecipeByFilter(session, filter);
        assertThat(recipeByFilter).hasSize(4);

        var authorName = recipeByFilter.stream()
                .map(Recipe::getAuthor)
                .map(Author::getName)
                .collect(toList());
        assertThat(authorName).containsOnly("Anna");

        session.getTransaction().commit();
    }

    @Test
    void findRecipeByFilterProducts() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        var productList = List.of(7L, 8L, 9L, 10L);
        var filter = RecipeFilter.builder()
                .products(productList)
                .build();

        var recipeByFilter = recipeDao.findRecipeByFilter(session, filter);
        assertThat(recipeByFilter).hasSize(4);

        var titles = recipeByFilter.stream()
                .map(Recipe::getTitle)
                .collect(toList());
        assertThat(titles).containsExactlyInAnyOrder("title10", "title9", "title8", "title7");

        session.getTransaction().commit();
    }

    @Test
    void findRecipeByFilterProductsAndCategory() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        var categoryRecipe = session.get(CategoryRecipe.class, 1L);
        var productList = List.of(7L, 8L, 9L, 6L);
        var filter = RecipeFilter.builder()
                .products(productList)
                .categoryRecipe(categoryRecipe.getId())
                .build();

        var recipeByFilter = recipeDao.findRecipeByFilter(session, filter);
        assertThat(recipeByFilter).hasSize(1);

        var titles = recipeByFilter.stream()
                .map(Recipe::getTitle)
                .collect(toList());
        assertThat(titles).containsExactlyInAnyOrder("title6");

        session.getTransaction().commit();
    }
}
