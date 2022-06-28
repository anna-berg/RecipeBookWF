package com.berg.dto;

import com.berg.entity.Author;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.List;

@Value
public class RecipeCreateDto {

    @NotNull
    String title;

    Author author;

    @NotNull
    String description;
    String measure;

    @NotNull
    CategoryRecipeReadDto categoryRecipe;

    @NotNull
    List<ProductReadDto> products;
}
