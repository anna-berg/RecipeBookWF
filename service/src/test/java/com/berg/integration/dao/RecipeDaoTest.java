package com.berg.integration.dao;

import com.berg.dto.RecipeFilter;
import com.berg.entity.Author;
import com.berg.entity.CategoryRecipe;
import com.berg.entity.Product;
import com.berg.entity.Recipe;
import com.berg.integration.IntegrationTestBase;
import com.berg.util.EntityHelper;
import com.berg.util.TestDataImporter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
//@TestInstance(PER_CLASS)
public class RecipeDaoTest extends IntegrationTestBase{

    private final RecipeDao recipeDao = RecipeDao.getInstance();
    private final EntityManager entityManager;
    private Recipe recipe;

    @BeforeEach
    public void initDb() {
        TestDataImporter.importData(entityManager);
        var categoryBreakfast = EntityHelper.createCategory();
        entityManager.persist(categoryBreakfast);
        recipe = EntityHelper.createRecipe(categoryBreakfast);
        entityManager.persist(recipe);
    }

    @Test
    void findAll() {
        List<Recipe> results = recipeDao.findAll(entityManager);
        assertThat(results).hasSize(11);

        List<String> titles = results.stream().map(Recipe::getTitle).collect(toList());
        assertThat(titles).containsExactlyInAnyOrder("title1", "title2", "title3", "title4", "title5",
                "title6", "title7", "title8", "title9", "title10");

        var productsName = results.get(3).getProducts().stream()
                .map(Product::getName)
                .collect(toList());
        assertThat(productsName).hasSize(4);
        assertThat(productsName).containsExactlyInAnyOrder("buckwheat", "cabbage", "carrot", "cucumber");
    }

    @Test
    void findRecipeByProductTest() {
        var product = entityManager.find(Product.class, 4L);
//        var product = EntityHelper.createProduct("cucumber", "vegetables");
        var recipeByProduct = recipeDao.findRecipeByProduct(entityManager, product);
        assertThat(recipeByProduct).hasSize(7);
    }

    @Test
    void findRecipeByFilterWithCategory() {
        var categoryRecipe = entityManager.find(CategoryRecipe.class, 1L);
        var filter = RecipeFilter.builder()
                .categoryRecipe(categoryRecipe.getId())
                .build();

        var recipeByFilter = recipeDao.findRecipeByFilter(entityManager, filter);
        assertThat(recipeByFilter).hasSize(2);

        var recipeTitles = recipeByFilter.stream()
                .map(Recipe::getTitle)
                .collect(toList());
        assertThat(recipeTitles).containsExactlyInAnyOrder("title1", "title6");
    }

    @Test
    void findRecipeByFilterWithAuthor() {
        var author = entityManager.find(Author.class, 2L);
        var filter = RecipeFilter.builder()
                .author(author.getId())
                .build();

        var recipeByFilter = recipeDao.findRecipeByFilter(entityManager, filter);
        assertThat(recipeByFilter).hasSize(4);

        var authorName = recipeByFilter.stream()
                .map(Recipe::getAuthor)
                .map(Author::getName)
                .collect(toList());
        assertThat(authorName).containsOnly("Anna");
    }

    @Test
    void findRecipeByFilterProducts() {
        var productList = List.of(7L, 8L, 9L, 10L);
        var filter = RecipeFilter.builder()
                .products(productList)
                .build();

        var recipeByFilter = recipeDao.findRecipeByFilter(entityManager, filter);
        assertThat(recipeByFilter).hasSize(4);

        var titles = recipeByFilter.stream()
                .map(Recipe::getTitle)
                .collect(toList());
        assertThat(titles).containsExactlyInAnyOrder("title10", "title9", "title8", "title7");
    }

    @Test
    void findRecipeByFilterProductsAndCategory() {
        var categoryRecipe = entityManager.find(CategoryRecipe.class, 1L);
        var productList = List.of(7L, 8L, 9L, 6L);
        var filter = RecipeFilter.builder()
                .products(productList)
                .categoryRecipe(categoryRecipe.getId())
                .build();

        var recipeByFilter = recipeDao.findRecipeByFilter(entityManager, filter);
        assertThat(recipeByFilter).hasSize(1);

        var titles = recipeByFilter.stream()
                .map(Recipe::getTitle)
                .collect(toList());
        assertThat(titles).containsExactlyInAnyOrder("title6");
    }
}
