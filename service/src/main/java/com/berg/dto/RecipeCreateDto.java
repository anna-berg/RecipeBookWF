package com.berg.dto;

import com.berg.entity.Author;
import com.berg.entity.CategoryRecipe;
import com.berg.entity.Product;

import java.util.List;

public record RecipeCreateDto (String title,
                               Author author,
                               String description,
                               String measure,
                               CategoryRecipe categoryRecipe,
                               List<Product> products){
}
