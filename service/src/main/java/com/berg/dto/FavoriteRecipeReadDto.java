package com.berg.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class FavoriteRecipeReadDto {

    Long id;
    UserReadDto user;
    RecipeReadDto recipe;
    int rating;
    LocalDateTime createdAt;
}
