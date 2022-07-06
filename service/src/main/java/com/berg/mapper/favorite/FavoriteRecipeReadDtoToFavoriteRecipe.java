package com.berg.mapper.favorite;

import com.berg.dto.favorite.FavoriteRecipeReadDto;
import com.berg.entity.FavoriteRecipe;
import com.berg.mapper.Mapper;
import com.berg.mapper.recipe.RecipeReadDtoToRecipeMapper;
import com.berg.mapper.user.UserReadDtoToUserMapper;
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
