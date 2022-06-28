package com.berg.dto;

import lombok.Value;

@Value
public class FavoriteRecipeDtoForUser{

    Long id;
    RecipeReadDto recipe;
    int rating;
}
