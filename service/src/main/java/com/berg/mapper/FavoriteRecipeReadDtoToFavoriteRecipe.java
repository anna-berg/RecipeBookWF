package com.berg.mapper;

import com.berg.dto.FavoriteRecipeReadDto;
import com.berg.entity.FavoriteRecipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FavoriteRecipeReadDtoToFavoriteRecipe implements Mapper<FavoriteRecipeReadDto, FavoriteRecipe> {

    private final UserReadDtoToUserMapper userReadDtoToUserMapper;
    private final RecipeReadDtoToRecipeMapper recipeReadDtoToRecipeMapper;

    @Override
    public FavoriteRecipe map(FavoriteRecipeReadDto object) {
        return FavoriteRecipe.builder()
                .id(object.getId())
                .user(userReadDtoToUserMapper.map(object.getUser()))
                .recipe(recipeReadDtoToRecipeMapper.map(object.getRecipe()))
                .rating(object.getRating())
                .createdAt(object.getCreatedAt())
                .build();
    }
}
