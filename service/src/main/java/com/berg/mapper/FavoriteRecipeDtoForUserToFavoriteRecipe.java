package com.berg.mapper;

import com.berg.dto.FavoriteRecipeDtoForUser;
import com.berg.entity.FavoriteRecipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FavoriteRecipeDtoForUserToFavoriteRecipe implements Mapper<FavoriteRecipeDtoForUser, FavoriteRecipe> {

    private final RecipeReadDtoToRecipeMapper recipeReadDtoToRecipeMapper;

    @Override
    public FavoriteRecipe map(FavoriteRecipeDtoForUser object) {
        return FavoriteRecipe.builder()
                .id(object.getId())
                .recipe(recipeReadDtoToRecipeMapper.map(object.getRecipe()))
                .rating(object.getRating())
                .build();
    }
}
