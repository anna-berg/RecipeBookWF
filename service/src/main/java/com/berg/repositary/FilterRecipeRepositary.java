package com.berg.repositary;

import com.berg.dto.recipe.RecipeFilter;
import com.berg.entity.Recipe;

import java.util.List;

public interface FilterRecipeRepositary {

    List<Recipe> findAllByFilter(RecipeFilter filter);
}
