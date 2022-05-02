package com.berg.dto;

import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@Builder
public class RecipeFilter {

    Long author;
    Long categoryRecipe;

    @Builder.Default
    List<Long> products = new ArrayList<>();
}
