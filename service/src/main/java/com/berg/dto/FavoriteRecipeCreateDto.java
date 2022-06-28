package com.berg.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class FavoriteRecipeCreateDto {

    UserReadDto user;
    RecipeReadDto recipe;
    int rating;
    LocalDateTime createdAt;
}
