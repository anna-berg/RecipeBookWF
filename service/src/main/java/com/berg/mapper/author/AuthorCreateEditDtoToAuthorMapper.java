package com.berg.mapper.author;

import com.berg.dto.author.AuthorCreateEditDto;
import com.berg.entity.Author;
import com.berg.mapper.Mapper;
import com.berg.mapper.recipe.RecipeReadDtoToRecipeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorCreateEditDtoToAuthorMapper implements Mapper<AuthorCreateEditDto, Author> {

    private final RecipeReadDtoToRecipeMapper recipeReadDtoToRecipeMapper;

    @Override
    public Author map(AuthorCreateEditDto fromObject, Author toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Author map(AuthorCreateEditDto object) {
        Author author = new Author();
        copy(object, author);
        return author;
    }

    private void copy(AuthorCreateEditDto object, Author author) {
        author.setName(object.getName());
    }
}
