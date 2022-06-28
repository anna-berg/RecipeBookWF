package com.berg.mapper;

import com.berg.dto.CategoryRecipeReadDto;
import com.berg.entity.CategoryRecipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryRecipeReadDtoToCategoryRecipe implements Mapper<CategoryRecipeReadDto, CategoryRecipe> {

    private final RecipeReadDtoToRecipeMapper recipeReadDtoToRecipeMapper;

    @Override
    public CategoryRecipe map(CategoryRecipeReadDto object) {
        return CategoryRecipe.builder()
                .id(object.getId())
                .category(object.getCategory())
                .build();
    }
}
