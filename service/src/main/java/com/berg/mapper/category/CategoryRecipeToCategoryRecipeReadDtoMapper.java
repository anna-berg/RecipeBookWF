package com.berg.mapper.category;

import com.berg.dto.category.CategoryRecipeReadDto;
import com.berg.entity.CategoryRecipe;
import com.berg.mapper.Mapper;
import com.berg.mapper.recipe.RecipeToRecipeDtoShortMapper;
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
