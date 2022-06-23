package com.berg.dto;

import com.berg.entity.Recipe;
import lombok.Value;

import java.util.List;

@Value
public class AuthorReadDto {

    Long id;
    String name;
    List<Recipe> recipes;
}
