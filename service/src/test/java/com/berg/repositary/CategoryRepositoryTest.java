package com.berg.repositary;

import com.berg.entity.CategoryRecipe;
import com.berg.integration.IntegrationTestBase;
import com.berg.util.EntityHelper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityManager;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Sql({
        "classpath:sql/data.sql"
})
@RequiredArgsConstructor
class CategoryRepositoryTest extends IntegrationTestBase {

    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager;
    CategoryRecipe categoryRecipe;

    @BeforeEach
    public void initDb() {
        categoryRecipe = EntityHelper.createCategory();
        categoryRepository.save(categoryRecipe);
    }

    @Test
    void checkSave() {
        entityManager.clear();
        var allCategory = categoryRepository.findAll();
        assertThat(allCategory).containsAnyOf(categoryRecipe);

    }

    @Test
    void checkFindAll() {
        var all = categoryRepository.findAll();
        assertThat(all).hasSize(6);
        var catigories = all.stream()
                .map(CategoryRecipe::getCategory)
                .collect(Collectors.toList());
        assertThat(catigories).containsExactlyInAnyOrder("breakfast",
                "first snack",
                "lunch",
                "second snack",
                "dinner",
                "TestBreakfast");
    }

    @Test
    void checkDelete() {
        categoryRepository.delete(categoryRecipe);
        entityManager.flush();
        var deletedCategory = categoryRepository.findById(categoryRecipe.getId());
        assertTrue(deletedCategory.isEmpty());
    }
}
