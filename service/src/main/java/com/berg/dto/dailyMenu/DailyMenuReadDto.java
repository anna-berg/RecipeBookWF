package com.berg.dto.dailyMenu;

import com.berg.dto.recipe.RecipeReadDto;
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
