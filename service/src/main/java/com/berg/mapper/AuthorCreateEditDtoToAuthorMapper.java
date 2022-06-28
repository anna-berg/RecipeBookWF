package com.berg.mapper;

import com.berg.dto.AuthorCreateEditDto;
import com.berg.entity.Author;
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
