package com.berg.mapper;

import com.berg.dto.AuthorReadDto;
import com.berg.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorReadMapper implements Mapper<Author, AuthorReadDto> {
    @Override
    public AuthorReadDto map(Author object) {
        return new AuthorReadDto(
                object.getId(),
                object.getName(),
                object.getRecipes());
    }
}
