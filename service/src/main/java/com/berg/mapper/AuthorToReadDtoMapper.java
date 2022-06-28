package com.berg.mapper;

import com.berg.dto.AuthorReadDto;
import com.berg.entity.Author;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorToReadDtoMapper implements Mapper<Author, AuthorReadDto> {

    private final RecipeToRecipeReadDtoMapper recipeToRecipeReadDtoMapper;

    @Override
    public AuthorReadDto map(Author object) {
        return new AuthorReadDto(
                object.getId(),
                object.getName(),
                ListUtils.emptyIfNull(object.getRecipes())
                        .stream()
                        .map(recipeToRecipeReadDtoMapper::map)
                        .toList());
    }
}
