package com.berg.service;

import com.berg.dto.CategoryCreateDto;
import com.berg.dto.CategoryRecipeReadDto;
import com.berg.mapper.CategoryCreateDtoToCategoryMapper;
import com.berg.mapper.CategoryRecipeToCategoryRecipeReadDtoMapper;
import com.berg.repositary.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryRecipeService {

    private final CategoryRepository categoryRepository;
    private final CategoryRecipeToCategoryRecipeReadDtoMapper categoryRecipeToCategoryRecipeReadDtoMapper;
    private final CategoryCreateDtoToCategoryMapper categoryCreateDtoToCategoryMapper;

    public List<CategoryRecipeReadDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryRecipeToCategoryRecipeReadDtoMapper::map)
                .toList();
    }

    public Optional<CategoryRecipeReadDto> findById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryRecipeToCategoryRecipeReadDtoMapper::map);
    }

    @Transactional
    public CategoryRecipeReadDto create(CategoryCreateDto categoryCreateDto) {
        return Optional.of(categoryCreateDto)
                .map(categoryCreateDtoToCategoryMapper::map)
                .map(categoryRepository::save)
                .map(categoryRecipeToCategoryRecipeReadDtoMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<CategoryRecipeReadDto> update(Long id, CategoryCreateDto categoryCreateDto) {
        return categoryRepository.findById(id)
                .map(categoryRecipe -> categoryCreateDtoToCategoryMapper.map(categoryCreateDto, categoryRecipe))
                .map(categoryRepository::saveAndFlush)
                .map(categoryRecipeToCategoryRecipeReadDtoMapper::map);
    }

    @Transactional
    public boolean delete(Long id){
        return categoryRepository.findById(id)
                .map(categoryRecipe -> {
                    categoryRepository.delete(categoryRecipe);
                    categoryRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
