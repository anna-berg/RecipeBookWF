package com.berg.dto;

import com.berg.entity.FavoriteRecipe;
import com.berg.entity.Gender;
import com.berg.entity.Groups;
import com.berg.entity.Role;

import java.util.List;

public record UserReadDto(Long id,
                          String name,
                          String email,
                          String password,
                          Role role,
                          Gender gender,
//                         FavoriteRecipe заенить на FavoriteRecipeДТО и Groups на Groups
                          List<FavoriteRecipe> favoriteRecipes,
                          List<Groups> groups) {

}
