package com.berg.dto;

import lombok.Value;

@Value
public class DailyMenuCreateDto {

    RecipeReadDto breakfast;
    RecipeReadDto firstSnack;
    RecipeReadDto lunch;
    RecipeReadDto secondSnack;
    RecipeReadDto dinner;
    String title;
}
