package com.berg.mapper;

import com.berg.dto.FavoriteRecipeReadDto;
import com.berg.entity.FavoriteRecipe;
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
