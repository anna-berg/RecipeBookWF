package com.berg.mapper;

import com.berg.dto.ProductReadDto;
import com.berg.entity.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RecipeReadMapper implements Mapper<Recipe, RecipeReadDto> {

    private final ProductReadMapper productReadMapper;

    @Override
    public RecipeReadDto map(Recipe object) {
        List<ProductReadDto> listOfProductDto = new ArrayList<>();

        return new RecipeReadDto(
                object.getId(),
                object.getTitle(),
                object.getAuthor(),
                object.getDescription(),
                object.getMeasure(),
                object.getCategoryRecipe(),
                Optional.ofNullable(object.getProducts().stream()
                        .map(productReadMapper::map)
                        .toList())
                        .orElse(listOfProductDto));
    }
}
