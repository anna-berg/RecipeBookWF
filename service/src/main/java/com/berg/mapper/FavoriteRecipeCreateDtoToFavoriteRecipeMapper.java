package com.berg.mapper;

import com.berg.dto.FavoriteRecipeCreateDto;
import com.berg.entity.FavoriteRecipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FavoriteRecipeCreateDtoToFavoriteRecipeMapper implements Mapper<FavoriteRecipeCreateDto, FavoriteRecipe> {

    private final UserReadDtoToUserMapper userReadDtoToUserMapper;
    private final RecipeReadDtoToRecipeMapper recipeReadDtoToRecipeMapper;

    @Override
    public FavoriteRecipe map(FavoriteRecipeCreateDto object) {
        return FavoriteRecipe.builder()
                .user(userReadDtoToUserMapper.map(object.getUser()))
                .recipe(recipeReadDtoToRecipeMapper.map(object.getRecipe()))
                .rating(object.getRating())
                .createdAt(object.getCreatedAt())
                .build();
    }
}
