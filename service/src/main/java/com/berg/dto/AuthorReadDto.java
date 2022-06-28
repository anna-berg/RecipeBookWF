package com.berg.dto;

import lombok.Value;

import java.util.List;

@Value
public class AuthorReadDto {

    Long id;
    String name;
    List<RecipeReadDto> recipes;
}
