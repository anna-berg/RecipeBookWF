package com.berg.mapper.favorite;

import com.berg.dto.favorite.FavoriteRecipeDtoForUser;
import com.berg.entity.FavoriteRecipe;
import com.berg.mapper.Mapper;
import com.berg.mapper.recipe.RecipeReadDtoToRecipeMapper;
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
