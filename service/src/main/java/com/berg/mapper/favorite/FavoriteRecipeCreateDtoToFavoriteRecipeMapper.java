package com.berg.mapper.favorite;

import com.berg.dto.favorite.FavoriteRecipeCreateDto;
import com.berg.entity.FavoriteRecipe;
import com.berg.mapper.Mapper;
import com.berg.mapper.recipe.RecipeReadDtoToRecipeMapper;
import com.berg.mapper.user.UserReadDtoToUserMapper;
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
