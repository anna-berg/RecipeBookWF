package com.berg.dto.favorite;

import com.berg.dto.recipe.RecipeReadDto;
import com.berg.dto.user.UserReadDto;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class FavoriteRecipeCreateDto {

    UserReadDto user;
    RecipeReadDto recipe;
    int rating;
    LocalDateTime createdAt;
}
