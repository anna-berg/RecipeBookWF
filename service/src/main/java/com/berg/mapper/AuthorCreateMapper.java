package com.berg.mapper;

import com.berg.dto.AuthorCreateDto;
import com.berg.entity.Author;

public class AuthorCreateMapper implements Mapper<AuthorCreateDto, Author> {
    @Override
    public Author mapFrom(AuthorCreateDto object) {
        return Author.builder()
                .name(object.name())
                .build();
    }
}
