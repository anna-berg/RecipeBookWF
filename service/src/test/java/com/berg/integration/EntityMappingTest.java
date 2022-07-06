package com.berg.integration;

import com.berg.mapper.recipe.RecipeToRecipeReadDtoMapper;
import com.berg.service.RecipeService;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
class EntityMappingTest extends IntegrationTestBase {

    private final RecipeService recipeService;
    private final RecipeToRecipeReadDtoMapper recipetoRecipeReadDtoMapper;
    private final EntityManager entityManager;
//    @Test
//    void SaveRecipeTest() {
//        try (var sessionFactory = HibernateUtil.buildSessionFactory();
//             var session = sessionFactory.openSession()) {
//            session.beginTransaction();
//
//
//            session.getTransaction().commit();
//        }
//    }
//
//    @Test
//    void createRecipeTest() {
//        var category = EntityHelper.createCategory();
//        entityManager.persist(category);
//        var recipe = EntityHelper.createRecipe(category);
//        var recipeDto = new RecipeCreateDto(recipe.getTitle(),
//                recipe.getAuthor(),
//                recipe.getDescription(),
//                recipe.getMeasure(),
//                recipe.getCategoryRecipe(),
//                recipe.getProducts());
//        recipeService.create(recipeDto);
//        entityManager.detach(recipe);
//        assertThat(recipe).isEqualTo(entityManager.find(Recipe.class, recipe.getId()));
//    }
//
//    @Test
//    void saveDailyMenu() {
//        var breakfast = entityManager.find(Recipe.class, 103L);
//        var firstSnack = entityManager.find(Recipe.class, 105L);
//        var lunch = entityManager.find(Recipe.class, 106L);
//        var secondSnack = entityManager.find(Recipe.class, 107L);
//        var dinner = entityManager.find(Recipe.class, 108L);
//
//        var dailyMenu = DailyMenu.builder()
//                .breakfast(breakfast)
//                .firstSnack(firstSnack)
//                .lunch(lunch)
//                .secondSnack(secondSnack)
//                .dinner(dinner)
//                .title("day 6")
//                .build();
//        entityManager.persist(dailyMenu);
//        assertThat(entityManager.find(DailyMenu.class, dailyMenu.getId())).isEqualTo(dailyMenu);
//    }
}
