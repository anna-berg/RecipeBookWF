package com.berg.dto.category;

import com.berg.dto.recipe.RecipeDtoShort;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.List;

@Value
public class CategoryRecipeReadDto {

    Long id;

    @NotNull
    String category;

    List<RecipeDtoShort> recipes;
}
