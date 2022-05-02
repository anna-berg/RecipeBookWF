package com.berg.mapper;

import com.berg.dto.AuthorReadDto;
import com.berg.entity.Author;

public class AuthorReadMapper implements Mapper<Author, AuthorReadDto> {
    @Override
    public AuthorReadDto mapFrom(Author object) {
        return new AuthorReadDto(
                object.getId(),
                object.getName(),
                object.getRecipes());
    }
}
