package com.berg.mapper.category;

import com.berg.dto.category.CategoryRecipeReadDto;
import com.berg.entity.CategoryRecipe;
import com.berg.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryRecipeReadDtoToCategoryRecipe implements Mapper<CategoryRecipeReadDto, CategoryRecipe> {

    @Override
    public CategoryRecipe map(CategoryRecipeReadDto object) {
        return CategoryRecipe.builder()
                .id(object.getId())
                .category(object.getCategory())
                .build();
    }
}
