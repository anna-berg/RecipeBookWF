package com.berg.mapper.recipe;

import com.berg.dto.recipe.RecipeReadDto;
import com.berg.entity.Recipe;
import com.berg.mapper.Mapper;
import com.berg.mapper.product.ProductToProductDtoForRecipe;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecipeToRecipeReadDtoMapper implements Mapper<Recipe, RecipeReadDto> {

    private final ProductToProductDtoForRecipe productToProductDtoForRecipe;

    @Override
    public RecipeReadDto map(Recipe object) {

        return new RecipeReadDto(
                object.getId(),
                object.getTitle(),
                object.getAuthor(),
                object.getDescription(),
                object.getMeasure(),
                object.getCategoryRecipe(),
                ListUtils.emptyIfNull(object.getProducts().stream()
                        .map(productToProductDtoForRecipe::map)
                        .toList())
        );
    }
}
