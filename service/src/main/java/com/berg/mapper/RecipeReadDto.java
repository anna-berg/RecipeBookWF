package com.berg.mapper;

import com.berg.dto.ProductReadDto;
import com.berg.entity.Author;
import com.berg.entity.CategoryRecipe;

import java.util.List;

public record RecipeReadDto(Long id,
                            String title,
                            Author author,
                            String description,
                            String measure,
                            CategoryRecipe categoryRecipe,
                            List<ProductReadDto> products) {
}
