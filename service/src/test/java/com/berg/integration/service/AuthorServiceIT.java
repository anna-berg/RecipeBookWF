package com.berg.integration.service;

import com.berg.dto.AuthorCreateEditDto;
import com.berg.dto.AuthorReadDto;
import com.berg.entity.Recipe;
import com.berg.integration.IntegrationTestBase;
import com.berg.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
class AuthorServiceIT extends IntegrationTestBase {

    private final static Long AUTHOR_ID = 1L;
    private final AuthorService authorService;

    @Test
    void findAll(){
        List<AuthorReadDto> result = authorService.findAll();
        assertThat(result).hasSize(2);
    }

    @Test
    void findById(){
        Optional<AuthorReadDto> maybeAuthor = authorService.findById(AUTHOR_ID);
        assertTrue(maybeAuthor.isPresent());
        maybeAuthor.ifPresent(author -> assertEquals("Sveta", author.getName()));
    }

    @Test
    void create(){
        List<Recipe> recipes = new ArrayList<>();
        var ivan = new AuthorCreateEditDto("Ivan", recipes);
        var actualResult = authorService.create(ivan);
        assertEquals(actualResult.getName(), ivan.getName());
    }

    @Test
    void update(){
        List<Recipe> recipes = new ArrayList<>();
        var ivan = new AuthorCreateEditDto("Ivan", recipes);
        var actualResult = authorService.update(AUTHOR_ID, ivan);
        actualResult.ifPresent(author -> assertEquals(author.getName(), ivan.getName()));
    }

    @Test
    void delete(){
        assertFalse(authorService.delete(-25L));
        assertTrue(authorService.delete(AUTHOR_ID));
    }
}