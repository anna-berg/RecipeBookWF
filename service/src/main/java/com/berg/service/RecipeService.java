package com.berg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeService {

//    private final RecipeRepository recipeRepository;
//    private final RecipeCreateMapper recipeCreateMapper;
//    private final RecipeReadMapper recipeReadMapper;
//
//    public Long create(RecipeCreateDto recipeDto) {
//        var recipeEntity = recipeCreateMapper.mapFrom(recipeDto);
//        return recipeRepository.save(recipeEntity).getId();
//    }
//
//    public Optional<RecipeReadDto> findById(Long id) {
//        return recipeRepository.findById(id)
//                .map(recipeReadMapper::mapFrom);
//    }
//
//    public boolean delete(Long id) {
//        var maybeRecipe = recipeRepository.findById(id);
//        maybeRecipe.ifPresent(recipe -> recipeRepository.delete(id));
//        return maybeRecipe.isPresent();
//    }
//
//    public void update(Recipe recipe) {
//        recipeRepository.update(recipe);
//    }
//
//    public List<RecipeReadDto> findAll() {
//        return recipeRepository.findAll().stream()
//                .map(recipeReadMapper::mapFrom)
//                .collect(toList());
//    }
}
