package com.berg.util;

import com.berg.entity.Author;
import com.berg.entity.CategoryRecipe;
import com.berg.entity.DailyMenu;
import com.berg.entity.Gender;
import com.berg.entity.GroupDay;
import com.berg.entity.Groups;
import com.berg.entity.Product;
import com.berg.entity.Recipe;
import com.berg.entity.Role;
import com.berg.entity.User;
import lombok.experimental.UtilityClass;

import javax.persistence.EntityManager;
import java.util.List;

@UtilityClass
public class TestDataImporter {

    public void importData(EntityManager entityManager){
//        entityManager.createNativeQuery("""
//                SELECT SETVAL('author_id_seq_id_seq', (1));
//                SELECT SETVAL('category_recipe_id_seq', (1));
//                SELECT SETVAL('daily_menu_id_seq', (1));
//                """);
        var authorSveta = saveAuthor(entityManager, "Sveta");
        var authorAnna = saveAuthor(entityManager, "Anna");

        var breakfastCategoryRecipe = saveCategoryRecipe(entityManager, "breakfast");
        var firstSnackCategoryRecipe = saveCategoryRecipe(entityManager, "first snack");
        var lunchCategoryRecipe = saveCategoryRecipe(entityManager, "lunch");
        var secondSnackCategoryRecipe = saveCategoryRecipe(entityManager, "second snack");
        var dinnerCategoryRecipe = saveCategoryRecipe(entityManager, "dinner");

        var product1 = saveProduct(entityManager, "buckwheat", "cereals");
        var product2 = saveProduct(entityManager, "cabbage", "vegetables");
        var product3 = saveProduct(entityManager, "carrot", "vegetables");
        var product4 = saveProduct(entityManager, "cucumber", "vegetables");
        var product5 = saveProduct(entityManager, "kiwi", "fruits");
        var product6 = saveProduct(entityManager, "orange", "fruits");
        var product7 = saveProduct(entityManager, "yogurt", "milk products");
        var product8 = saveProduct(entityManager, "baked milk", "milk products");
        var product9 = saveProduct(entityManager, "cocoa", "seasonings");
        var product10 = saveProduct(entityManager, "gelatin", "seasonings");
        var product11 = saveProduct(entityManager, "apple", "fruits");

        var recipe1 = saveRecipe(entityManager, authorAnna, "title1", "description1", breakfastCategoryRecipe,
                List.of(product1));
        var recipe2 = saveRecipe(entityManager, authorAnna, "title2", "description2", firstSnackCategoryRecipe,
                List.of(product1, product2));
        var recipe3 = saveRecipe(entityManager, authorAnna, "title3", "description3", lunchCategoryRecipe,
                List.of(product1, product2, product3));
        var recipe4 = saveRecipe(entityManager, authorAnna, "title4", "description4", secondSnackCategoryRecipe,
                List.of(product1, product2, product3, product4));
        var recipe5 = saveRecipe(entityManager, authorSveta, "title5", "description5", dinnerCategoryRecipe,
                List.of(product1, product2, product3, product4, product5));
        var recipe6 = saveRecipe(entityManager, authorSveta, "title6", "description6", breakfastCategoryRecipe,
                List.of(product1, product2, product3, product4, product5, product6));
        var recipe7 = saveRecipe(entityManager, authorSveta, "title7", "description7", firstSnackCategoryRecipe,
                List.of(product1, product2, product3, product4, product5, product6, product7));
        var recipe8 = saveRecipe(entityManager, authorSveta, "title8", "description8", lunchCategoryRecipe,
                List.of(product1, product2, product3, product4, product5, product6, product7, product8));
        var recipe9 = saveRecipe(entityManager, authorSveta, "title9", "description9", secondSnackCategoryRecipe,
                List.of(product1, product2, product3, product4, product5, product6, product7, product8, product9));
        var recipe10 = saveRecipe(entityManager, authorSveta, "title10", "description10", dinnerCategoryRecipe,
                List.of(product1, product2, product3, product4, product5, product6, product7, product8, product9, product10));

        var dailyMenu1 = saveDailyMenu(entityManager, "TitleDailyMenu1", recipe1, recipe2, recipe3, recipe4, recipe5);
        var dailyMenu2 = saveDailyMenu(entityManager, "TitleDailyMenu2", recipe6, recipe7, recipe8, recipe9, recipe10);

        var ivan = saveUser(entityManager, "ivan@test.com", "111", Role.ADMIN, Gender.MALE, "Ivan");
        var sveta = saveUser(entityManager, "sveta@test.com", "111", Role.USER, Gender.FEMALE, "Sveta");

        var groupIvan = saveGroup(entityManager, "GroupTitle", ivan);

        var groupDay1 = saveGroupDay(entityManager, groupIvan, dailyMenu1, 1);
        var groupDay2 = saveGroupDay(entityManager, groupIvan, dailyMenu2, 2);
    }

    private Author saveAuthor(EntityManager entityManager, String name){
        var author = Author.builder()
                .name(name)
                .build();
        entityManager.persist(author);
        return author;
    }

    private CategoryRecipe saveCategoryRecipe(EntityManager entityManager, String category){
        var recipe = CategoryRecipe.builder()
                .category(category)
                .build();
        entityManager.persist(recipe);
        return recipe;
    }

    private DailyMenu saveDailyMenu(EntityManager entityManager, String title, Recipe breakfast,
                                    Recipe firstSnack, Recipe lunch, Recipe secondSnack,
                                    Recipe dinner){
        DailyMenu dailyMenu = DailyMenu.builder()
                .title(title)
                .breakfast(breakfast)
                .firstSnack(firstSnack)
                .lunch(lunch)
                .secondSnack(secondSnack)
                .dinner(dinner)
                .build();
        entityManager.persist(dailyMenu);
        return dailyMenu;
    }

    private GroupDay saveGroupDay (EntityManager entityManager, Groups group, DailyMenu dailyMenu, int position){
        GroupDay groupDay = GroupDay.builder()
                .group(group)
                .dailyMenu(dailyMenu)
                .position(position)
                .build();
        entityManager.persist(groupDay);
        return groupDay;
    }

    private Groups saveGroup(EntityManager entityManager, String title, User user){
        Groups group = Groups.builder()
                .groupTitle(title)
                .user(user)
                .build();
        entityManager.persist(group);
        return group;
    }

    private Product saveProduct(EntityManager entityManager, String name, String productType){
        var product = Product.builder()
                .name(name)
                .type(productType)
                .build();
        entityManager.persist(product);
        return product;
    }

    private Recipe saveRecipe(EntityManager entityManager, Author author, String title, String description,
                              CategoryRecipe categoryRecipe, List<Product> products){
        var recipe = Recipe.builder()
                .author(author)
                .title(title)
                .description(description)
                .categoryRecipe(categoryRecipe)
                .products(products)
                .build();
         entityManager.persist(recipe);
        return recipe;
    }

    private User saveUser(EntityManager entityManager, String email, String password,
                          Role role, Gender gender, String name){
        User user = User.builder()
                .email(email)
                .password(password)
                .role(role)
                .gender(gender)
                .name(name)
                .build();
        entityManager.persist(user);
        return user;
    }
}
