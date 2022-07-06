package com.berg.mapper.favorite;

import com.berg.dto.favorite.FavoriteRecipeDtoForUser;
import com.berg.entity.FavoriteRecipe;
import com.berg.mapper.Mapper;
import com.berg.mapper.recipe.RecipeToRecipeReadDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FavoriteRecipeToDtoForUserMapper implements Mapper<FavoriteRecipe, FavoriteRecipeDtoForUser> {

    private final RecipeToRecipeReadDtoMapper recipeToRecipeReadDtoMapper;

    @Override
    public FavoriteRecipeDtoForUser map(FavoriteRecipe object) {
        return new FavoriteRecipeDtoForUser(
                object.getId(),
                recipeToRecipeReadDtoMapper.map(object.getRecipe()),
                object.getRating()
        );
    }
}
