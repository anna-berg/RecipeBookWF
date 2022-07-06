package com.berg.mapper.recipe;

import com.berg.dto.recipe.RecipeReadDto;
import com.berg.entity.Recipe;
import com.berg.mapper.Mapper;
import com.berg.mapper.product.ProductDtoForRecipeToProduct;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecipeReadDtoToRecipeMapper implements Mapper<RecipeReadDto, Recipe> {

    private final ProductDtoForRecipeToProduct productDtoForRecipeToProduct;

    @Override
    public Recipe map(RecipeReadDto object) {
        return Recipe.builder()
                .id(object.id())
                .title(object.title())
                .author(object.author())
                .description(object.description())
                .measure(object.measure())
                .categoryRecipe(object.categoryRecipe())
                .products(ListUtils.emptyIfNull(object.products().stream()
                        .map(productDtoForRecipeToProduct::map)
                        .toList()))
                .build();
    }
}
