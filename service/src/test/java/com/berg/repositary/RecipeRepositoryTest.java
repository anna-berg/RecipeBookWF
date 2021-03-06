package com.berg.repositary;

import com.berg.entity.CategoryRecipe;
import com.berg.entity.Product;
import com.berg.entity.Recipe;
import com.berg.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityManager;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Sql({
        "classpath:sql/data.sql"
})
@RequiredArgsConstructor
class RecipeRepositoryTest extends IntegrationTestBase {

    private final RecipeRepository recipeRepository;
    private final EntityManager entityManager;
    private Recipe recipe;
    private CategoryRecipe categoryRecipe;

    @Test
    void checkFindAllByCategory() {
        var allBreakfasts = recipeRepository.findAllByCategory("breakfast");
        assertThat(allBreakfasts).hasSize(2);
        var descriptions = allBreakfasts.stream()
                .map(Recipe::getDescription)
                .collect(Collectors.toList());
        assertThat(descriptions).containsExactlyInAnyOrder("description1", "description6");
    }

    @Test
    void checkFindByAuthor() {
        var recipeList = recipeRepository.findAllByAuthor("Anna");
        assertThat(recipeList).hasSize(4);
        var recipeDescriptions = recipeList.stream()
                .map(Recipe::getTitle)
                .collect(Collectors.toList());
        assertThat(recipeDescriptions).containsExactlyInAnyOrder("title1", "title2", "title3", "title4");
    }

    @Test
    void checkFindByProducts() {
        var product = entityManager.find(Product.class, 1L);
        var product2 = entityManager.find(Product.class, 2L);

        var recipes = recipeRepository.findAllByProducts(9L, 10L);

        recipes.forEach(System.out::println);
    }

}