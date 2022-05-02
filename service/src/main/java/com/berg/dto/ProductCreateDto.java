package com.berg.dto;

import com.berg.entity.Recipe;

import java.util.List;

public record ProductCreateDto(
        String name,
        Integer proteins,
        Integer fats,
        Integer carbohydrates,
        String type,
        List<Recipe> recipes) {
}
