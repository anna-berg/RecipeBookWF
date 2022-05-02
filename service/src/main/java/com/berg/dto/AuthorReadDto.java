package com.berg.dto;

import com.berg.entity.Recipe;

import java.util.List;

public record AuthorReadDto(Long id,
                            String name,
                            List<Recipe> recipes) {
}
