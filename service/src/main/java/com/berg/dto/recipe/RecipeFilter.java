package com.berg.dto.recipe;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RecipeFilter {

    String author;
    String categoryRecipe;
    String products;
}
