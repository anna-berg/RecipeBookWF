package com.berg.mapper.favorite;

import com.berg.dto.favorite.FavoriteRecipeReadDto;
import com.berg.entity.FavoriteRecipe;
import com.berg.mapper.Mapper;
import com.berg.mapper.recipe.RecipeToRecipeReadDtoMapper;
import com.berg.mapper.user.UserToReadDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FavoriteRecipeToReadDtoMapper implements Mapper<FavoriteRecipe, FavoriteRecipeReadDto> {

    private final UserToReadDtoMapper userToReadDtoMapper;
    private final RecipeToRecipeReadDtoMapper recipetoRecipeReadDtoMapper;

    @Override
    public FavoriteRecipeReadDto map(FavoriteRecipe object) {
        return new FavoriteRecipeReadDto(
                object.getId(),
                userToReadDtoMapper.map(object.getUser()),
                recipetoRecipeReadDtoMapper.map(object.getRecipe()),
                object.getRating(),
                object.getCreatedAt()
        );
    }
}
