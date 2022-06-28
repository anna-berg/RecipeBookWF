package com.berg.mapper;

import com.berg.dto.FavoriteRecipeDtoForUser;
import com.berg.entity.FavoriteRecipe;
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
