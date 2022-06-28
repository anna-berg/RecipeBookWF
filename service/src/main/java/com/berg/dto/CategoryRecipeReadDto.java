package com.berg.dto;

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
