package com.berg.util;

import com.berg.service.entity.Author;
import com.berg.service.entity.CategoryRecipe;
import com.berg.service.entity.DailyMenu;
import com.berg.service.entity.FavoriteRecipe;
import com.berg.service.entity.Gender;
import com.berg.service.entity.GroupDay;
import com.berg.service.entity.Groups;
import com.berg.service.entity.Product;
import com.berg.service.entity.Recipe;
import com.berg.service.entity.Role;
import com.berg.service.entity.User;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@UtilityClass
public class TestDataImporter {

    public void importData(SessionFactory sessionFactory){
        @Cleanup Session session = sessionFactory.openSession();

        var authorSveta = saveAuthor(session, "Sveta");
        var authorAnna = saveAuthor(session, "Anna");

        var breakfastCategoryRecipe = saveCategoryRecipe(session, "breakfast");
        var firstSnackCategoryRecipe = saveCategoryRecipe(session, "first snack");
        var lunchCategoryRecipe = saveCategoryRecipe(session, "lunch");
        var secondSnackCategoryRecipe = saveCategoryRecipe(session, "second snack");
        var dinnerCategoryRecipe = saveCategoryRecipe(session, "dinner");

        var product1 = saveProduct(session, "buckwheat", "cereals");
        var product2 = saveProduct(session, "cabbage", "vegetables");
        var product3 = saveProduct(session, "carrot", "vegetables");
        var product4 = saveProduct(session, "cucumber", "vegetables");
        var product5 = saveProduct(session, "kiwi", "fruits");
        var product6 = saveProduct(session, "orange", "fruits");
        var product7 = saveProduct(session, "yogurt", "milk products");
        var product8 = saveProduct(session, "baked milk", "milk products");
        var product9 = saveProduct(session, "cocoa", "seasonings");
        var product10 = saveProduct(session, "gelatin", "seasonings");
        var product11 = saveProduct(session, "apple", "fruits");

        var recipe1 = saveRecipe(session, authorAnna, "title1", "description1", breakfastCategoryRecipe,
                List.of(product1));
        var recipe2 = saveRecipe(session, authorAnna, "title2", "description2", firstSnackCategoryRecipe,
                List.of(product1, product2));
        var recipe3 = saveRecipe(session, authorAnna, "title3", "description3", lunchCategoryRecipe,
                List.of(product1, product2, product3));
        var recipe4 = saveRecipe(session, authorAnna, "title4", "description4", secondSnackCategoryRecipe,
                List.of(product1, product2, product3, product4));
        var recipe5 = saveRecipe(session, authorAnna, "title5", "description5", dinnerCategoryRecipe,
                List.of(product1, product2, product3, product4, product5));
        var recipe6 = saveRecipe(session, authorAnna, "title6", "description6", breakfastCategoryRecipe,
                List.of(product1, product2, product3, product4, product5, product6));
        var recipe7 = saveRecipe(session, authorAnna, "title7", "description7", firstSnackCategoryRecipe,
                List.of(product1, product2, product3, product4, product5, product6, product7));
        var recipe8 = saveRecipe(session, authorAnna, "title8", "description8", lunchCategoryRecipe,
                List.of(product1, product2, product3, product4, product5, product6, product7, product8));
        var recipe9 = saveRecipe(session, authorAnna, "title9", "description9", secondSnackCategoryRecipe,
                List.of(product1, product2, product3, product4, product5, product6, product7, product8, product9));
        var recipe10 = saveRecipe(session, authorAnna, "title10", "description10", dinnerCategoryRecipe,
                List.of(product1, product2, product3, product4, product5, product6, product7, product8, product9, product10));

        var dailyMenu1 = saveDailyMenu(session, "TitleDailyMenu1", recipe1, recipe2, recipe3, recipe4, recipe5);
        var dailyMenu2 = saveDailyMenu(session, "TitleDailyMenu2", recipe6, recipe7, recipe8, recipe9, recipe10);

        var ivan = saveUser(session, "ivan@test.com", "111", Role.ADMIN, Gender.MALE, "Ivan");
        var sveta = saveUser(session, "sveta@test.com", "111", Role.USER, Gender.FEMALE, "Sveta");

        var groupIvan = saveGroup(session, "GroupTitle", ivan);

        var groupDay1 = saveGroupDay(session, groupIvan, dailyMenu1, 1);
        var groupDay2 = saveGroupDay(session, groupIvan, dailyMenu2, 2);

        session.beginTransaction();
        session.flush();
        session.getTransaction().commit();
    }

    private Author saveAuthor(Session session, String name){
        var author = Author.builder()
                .name(name)
                .build();
        session.save(author);
        return author;
    }

    private CategoryRecipe saveCategoryRecipe(Session session, String category){
        var recipe = CategoryRecipe.builder()
                .category(category)
                .build();
        session.save(recipe);
        return recipe;
    }

    private DailyMenu saveDailyMenu(Session session, String title, Recipe breakfast,
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
        session.save(dailyMenu);
        return dailyMenu;
    }

    private GroupDay saveGroupDay (Session session, Groups group, DailyMenu dailyMenu, int position){
        GroupDay groupDay = GroupDay.builder()
                .group(group)
                .dailyMenu(dailyMenu)
                .position(position)
                .build();
        session.save(groupDay);
        return groupDay;
    }

    private Groups saveGroup(Session session, String title, User user){
        Groups group = Groups.builder()
                .groupTitle(title)
                .user(user)
                .build();
        session.save(group);
        return group;
    }

    private Product saveProduct(Session session, String name, String productType){
        var product = Product.builder()
                .name(name)
                .type(productType)
                .build();
        session.save(product);
        return product;
    }

    private Recipe saveRecipe(Session session, Author author, String title, String description,
                              CategoryRecipe categoryRecipe, List<Product> products){
        var recipe = Recipe.builder()
                .author(author)
                .title(title)
                .description(description)
                .categoryRecipe(categoryRecipe)
                .products(products)
                .build();
        session.save(recipe);
        return recipe;
    }
    private User saveUser(Session session, String email, String password,
                          Role role, Gender gender, String name){
        User user = User.builder()
                .email(email)
                .password(password)
                .role(role)
                .gender(gender)
                .name(name)
                .build();
        session.save(user);
        return user;
    }
}
