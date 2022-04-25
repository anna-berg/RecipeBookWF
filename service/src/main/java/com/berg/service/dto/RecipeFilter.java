package com.berg.service.dto;

import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@Builder
public class RecipeFilter {

    Long author;
    Long categoryRecipe;
    List<Long> products = new ArrayList<>();
}
