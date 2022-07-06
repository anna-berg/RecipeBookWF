package com.berg.dto.author;

import com.berg.dto.recipe.RecipeReadDto;
import lombok.Value;

import java.util.List;

@Value
public class AuthorReadDto {

    Long id;
    String name;
    List<RecipeReadDto> recipes;
}
