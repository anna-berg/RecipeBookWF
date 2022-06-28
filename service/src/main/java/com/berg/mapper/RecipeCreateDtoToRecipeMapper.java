package com.berg.mapper;

import com.berg.dto.RecipeCreateDto;
import com.berg.entity.Recipe;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecipeCreateDtoToRecipeMapper implements Mapper<RecipeCreateDto, Recipe> {

    private final CategoryRecipeReadDtoToCategoryRecipe categoryRecipeReadDtoToCategoryRecipe;
    private final ProductReadDtoToProductMapper productReadDtoToProductMapper;

    @Override
    public Recipe map(RecipeCreateDto object) {
        return Recipe.builder()
                .author(object.getAuthor())
                .categoryRecipe(
                        categoryRecipeReadDtoToCategoryRecipe.map(object.getCategoryRecipe()))
                .title(object.getTitle())
                .description(object.getDescription())
                .measure(object.getMeasure())
                .products(ListUtils.emptyIfNull(object.getProducts()).stream()
                        .map(productReadDtoToProductMapper::map)
                        .toList())
                .build();
    }
}