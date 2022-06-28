package com.berg.service;

import com.berg.dto.RecipeCreateDto;
import com.berg.dto.RecipeReadDto;
import com.berg.mapper.RecipeCreateDtoToRecipeMapper;
import com.berg.mapper.RecipeToRecipeReadDtoMapper;
import com.berg.repositary.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCreateDtoToRecipeMapper recipeCreateDtoToRecipeMapper;
    private final RecipeToRecipeReadDtoMapper recipetoRecipeReadDtoMapper;

    public List<RecipeReadDto> findAll() {
        return recipeRepository.findAll().stream()
                .map(recipetoRecipeReadDtoMapper::map)
                .toList();
    }

    public Optional<RecipeReadDto> findById(Long id) {
        return recipeRepository.findById(id)
                .map(recipetoRecipeReadDtoMapper::map);
    }

    @Transactional
    public RecipeReadDto create(RecipeCreateDto recipeDto) {
        return Optional.of(recipeDto)
                .map(recipeCreateDtoToRecipeMapper::map)
                .map(recipeRepository::save)
                .map(recipetoRecipeReadDtoMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<RecipeReadDto> update(Long id, RecipeCreateDto recipeDto) {
        return recipeRepository.findById(id)
                .map(recipe -> recipeCreateDtoToRecipeMapper.map(recipeDto, recipe))
                .map(recipeRepository::saveAndFlush)
                .map(recipetoRecipeReadDtoMapper::map);
    }

    public boolean delete(Long id) {
        return recipeRepository.findById(id)
                .map(recipe -> {
                    recipeRepository.delete(recipe);
                    recipeRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
