package com.berg.mapper;

import com.berg.dto.RecipeCreateDto;
import com.berg.entity.Recipe;

public class RecipeCreateMapper implements Mapper<RecipeCreateDto, Recipe> {
    @Override
    public Recipe mapFrom(RecipeCreateDto object) {
        return Recipe.builder()
                .author(object.author())
                .categoryRecipe(object.categoryRecipe())
                .title(object.title())
                .description(object.description())
                .measure(object.measure())
                .products(object.products())
                .build();
    }
}
