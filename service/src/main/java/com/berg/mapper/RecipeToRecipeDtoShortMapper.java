package com.berg.mapper;

import com.berg.dto.RecipeDtoShort;
import com.berg.entity.Recipe;
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
