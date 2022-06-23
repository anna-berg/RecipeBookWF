package com.berg.service;

import com.berg.dto.RecipeCreateDto;
import com.berg.mapper.RecipeCreateMapper;
import com.berg.mapper.RecipeReadDto;
import com.berg.mapper.RecipeReadMapper;
import com.berg.repositary.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCreateMapper recipeCreateMapper;
    private final RecipeReadMapper recipeReadMapper;

    public List<RecipeReadDto> findAll() {
        return recipeRepository.findAll().stream()
                .map(recipeReadMapper::map)
                .collect(toList());
    }

    public Optional<RecipeReadDto> findById(Long id) {
        return recipeRepository.findById(id)
                .map(recipeReadMapper::map);
    }

    @Transactional
    public RecipeReadDto create(RecipeCreateDto recipeDto) {
        var recipeEntity = recipeCreateMapper.map(recipeDto);
        return Optional.of(recipeDto)
                .map(recipeCreateMapper::map)
                .map(recipeRepository::save)
                .map(recipeReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<RecipeReadDto> update(Long id, RecipeCreateDto recipeDto) {
        return recipeRepository.findById(id)
                .map(recipe -> recipeCreateMapper.map(recipeDto, recipe))
                .map(recipeRepository::saveAndFlush)
                .map(recipeReadMapper::map);
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
