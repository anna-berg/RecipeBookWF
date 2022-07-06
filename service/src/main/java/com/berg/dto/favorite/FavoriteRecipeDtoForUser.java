package com.berg.dto.favorite;

import com.berg.dto.recipe.RecipeReadDto;
import lombok.Value;

@Value
public class FavoriteRecipeDtoForUser{

    Long id;
    RecipeReadDto recipe;
    int rating;
}
