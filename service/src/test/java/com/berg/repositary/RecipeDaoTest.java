package com.berg.repositary;

import com.berg.entity.Recipe;
import com.berg.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
//@TestInstance(PER_CLASS)
public class RecipeDaoTest extends IntegrationTestBase {

    private final RecipeRepository recipeRepository;
    private final EntityManager entityManager;
    private Recipe recipe;

//    @BeforeEach
//    public void initDb() {
//        TestDataImporter.importData(entityManager);
//        var categoryBreakfast = EntityHelper.createCategory();
//        entityManager.persist(categoryBreakfast);
//        recipe = EntityHelper.createRecipe(categoryBreakfast);
//        entityManager.persist(recipe);
//    }
//
//    @Test
//    void findAll() {
//        List<Recipe> results = recipeRepository.findAll(entityManager);
//        assertThat(results).hasSize(11);
//
//        List<String> titles = results.stream().map(Recipe::getTitle).collect(toList());
//        assertThat(titles).containsExactlyInAnyOrder("title1", "title2", "title3", "title4", "title5",
//                "title6", "title7", "title8", "title9", "title10", "Recipe title");
//
//        var productsName = results.get(3).getProducts().stream()
//                .map(Product::getName)
//                .collect(toList());
//        assertThat(productsName).hasSize(4)
//                .containsExactlyInAnyOrder("buckwheat", "cabbage", "carrot", "cucumber");
//    }
//
//    @Test
//    void findRecipeByFilterWithAuthor() {
//        var author = entityManager.find(Author.class, 2L);
//        var filter = RecipeFilter.builder()
//                .author(author.getId())
//                .build();
//
//        var recipeByFilter = recipeDao.findRecipeByFilter(entityManager, filter);
//        assertThat(recipeByFilter).hasSize(4);
//
//        var authorName = recipeByFilter.stream()
//                .map(Recipe::getAuthor)
//                .map(Author::getName)
//                .collect(toList());
//        assertThat(authorName).containsOnly("Anna");
//    }
//
//    @Test
//    void findRecipeByFilterProducts() {
//        var productList = List.of(7L, 8L, 9L, 10L);
//        var filter = RecipeFilter.builder()
//                .products(productList)
//                .build();
//
//        var recipeByFilter = recipeDao.findRecipeByFilter(entityManager, filter);
//        assertThat(recipeByFilter).hasSize(4);
//
//        var titles = recipeByFilter.stream()
//                .map(Recipe::getTitle)
//                .collect(toList());
//        assertThat(titles).containsExactlyInAnyOrder("title10", "title9", "title8", "title7");
//    }
//
//    @Test
//    void findRecipeByFilterProductsAndCategory() {
//        var categoryRecipe = entityManager.find(CategoryRecipe.class, 1L);
//        var productList = List.of(7L, 8L, 9L, 6L);
//        var filter = RecipeFilter.builder()
//                .products(productList)
//                .categoryRecipe(categoryRecipe.getId())
//                .build();
//
//        var recipeByFilter = recipeDao.findRecipeByFilter(entityManager, filter);
//        assertThat(recipeByFilter).hasSize(1);
//
//        var titles = recipeByFilter.stream()
//                .map(Recipe::getTitle)
//                .collect(toList());
//        assertThat(titles).containsExactlyInAnyOrder("title6");
//    }
}
