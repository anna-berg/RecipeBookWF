package com.berg.dto;

import com.berg.entity.FavoriteRecipe;
import com.berg.entity.Gender;
import com.berg.entity.Groups;
import com.berg.entity.Role;

import java.util.List;

public record UserCreateDto(
        String name,
        String email,
        String password,
        Role role,
        Gender gender,
        List<FavoriteRecipe> favoriteRecipes,
        List<Groups> groups) {
}
