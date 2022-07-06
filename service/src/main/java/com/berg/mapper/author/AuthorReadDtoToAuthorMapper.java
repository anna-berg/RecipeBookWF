package com.berg.mapper.author;

import com.berg.dto.author.AuthorReadDto;
import com.berg.entity.Author;
import com.berg.mapper.Mapper;
import com.berg.mapper.recipe.RecipeReadDtoToRecipeMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorReadDtoToAuthorMapper implements Mapper<AuthorReadDto, Author> {

    private final RecipeReadDtoToRecipeMapper recipeReadDtoToRecipeMapper;

    @Override
    public Author map(AuthorReadDto object) {
        return Author.builder()
                .id(object.getId())
                .name(object.getName())
                .recipes(ListUtils.emptyIfNull(object.getRecipes())
                        .stream()
                        .map(recipeReadDtoToRecipeMapper::map)
                        .toList())
                .build();
    }
}
