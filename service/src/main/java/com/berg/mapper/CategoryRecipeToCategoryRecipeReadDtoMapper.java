package com.berg.mapper;

import com.berg.dto.CategoryRecipeReadDto;
import com.berg.entity.CategoryRecipe;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryRecipeToCategoryRecipeReadDtoMapper implements Mapper<CategoryRecipe, CategoryRecipeReadDto> {

    private final RecipeToRecipeDtoShortMapper recipeToRecipeDtoShortMapper;
    @Override
    public CategoryRecipeReadDto map(CategoryRecipe object) {
        return new CategoryRecipeReadDto(
                object.getId(),
                object.getCategory(),
                ListUtils.emptyIfNull(object.getRecipes().stream()
                        .map(recipeToRecipeDtoShortMapper::map)
                        .toList())
        );
    }
}
