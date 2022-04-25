package com.berg.util;

import com.berg.service.entity.Author;
import com.berg.service.entity.CategoryRecipe;
import com.berg.service.entity.DailyMenu;
import com.berg.service.entity.Gender;
import com.berg.service.entity.Product;
import com.berg.service.entity.Recipe;
import com.berg.service.entity.Role;
import com.berg.service.entity.User;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class EntityHelper {

    public Author createAuthor() {
        return Author.builder()
                .name("Sveta")
                .build();
    }

    public CategoryRecipe createCategory() {
        return CategoryRecipe.builder()
                .category("Breakfast")
                .build();
    }

    public DailyMenu createDailyMenu() {
        return DailyMenu.builder()
                .title("test daily menu")
                .build();
    }

    public Product createProduct(String name, String type) {
        return Product.builder()
                .name(name)
                .type(type)
                .build();
    }

    public Recipe createRecipe() {
        var category = createCategory();
        return Recipe.builder()
                .categoryRecipe(category)
                .title("Recipe title")
                .description("Description for recipe")
                .measure("measure")
                .products(List.of(
                        Product.builder()
                                .name("onion")
                                .build(),
                        Product.builder()
                                .name("potato")
                                .build(),
                        Product.builder()
                                .name("cucumber")
                                .build()))
                .build();
    }

    public User createUser() {
        return User.builder()
                .name("Alex")
                .email("alex@test.com")
                .password("111")
                .gender(Gender.MALE)
                .role(Role.ADMIN)
                .build();
    }
}
