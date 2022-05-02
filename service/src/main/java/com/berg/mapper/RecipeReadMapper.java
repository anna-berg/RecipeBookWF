package com.berg.mapper;

import com.berg.entity.Recipe;

public class RecipeReadMapper implements Mapper<Recipe, RecipeReadDto> {

    @Override
    public RecipeReadDto mapFrom(Recipe object) {
        return new RecipeReadDto(
                object.getId(),
                object.getTitle(),
                object.getAuthor(),
                object.getDescription(),
                object.getMeasure(),
                object.getCategoryRecipe(),
                object.getProducts()
        );
    }
}
