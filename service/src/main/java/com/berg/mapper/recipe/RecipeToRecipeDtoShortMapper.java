package com.berg.mapper.recipe;

import com.berg.dto.recipe.RecipeDtoShort;
import com.berg.entity.Recipe;
import com.berg.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeDtoShortMapper implements Mapper<Recipe, RecipeDtoShort> {
    @Override
    public RecipeDtoShort map(Recipe object) {
        return new RecipeDtoShort(
                object.getId(),
                object.getTitle()
        );
    }
}
