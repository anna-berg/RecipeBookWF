package com.berg.dto;

import lombok.Value;

@Value
public class DailyMenuReadDto {

    Long id;
    RecipeReadDto breakfast;
    RecipeReadDto firstSnack;
    RecipeReadDto lunch;
    RecipeReadDto secondSnack;
    RecipeReadDto dinner;
    String title;
}
