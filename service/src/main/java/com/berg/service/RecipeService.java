package com.berg.service;

import com.berg.dao.RecipeRepository;
import com.berg.dto.RecipeCreateDto;
import com.berg.entity.Recipe;
import com.berg.mapper.RecipeCreateMapper;
import com.berg.mapper.RecipeReadDto;
import com.berg.mapper.RecipeReadMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class RecipeService {
    
    private final RecipeRepository recipeRepository;
    private final RecipeCreateMapper recipeCreateMapper;
    private final RecipeReadMapper recipeReadMapper;

    public Long create (RecipeCreateDto recipeDto){
        var recipeEntity = recipeCreateMapper.mapFrom(recipeDto);
        return recipeRepository.save(recipeEntity).getId();
    }

    public Optional<RecipeReadDto> findById(Long id){
        return recipeRepository.findById(id)
                .map(recipeReadMapper::mapFrom);
    }

    public boolean delete(Long id){
        var maybeRecipe = recipeRepository.findById(id);
        maybeRecipe.ifPresent(recipe -> recipeRepository.delete(id));
        return maybeRecipe.isPresent();
    }

    public void update(Recipe recipe){
        recipeRepository.update(recipe);
    }

    public List<RecipeReadDto> findAll(){
        return recipeRepository.findAll().stream()
                .map(recipeReadMapper::mapFrom)
                .collect(toList());
    }
}
