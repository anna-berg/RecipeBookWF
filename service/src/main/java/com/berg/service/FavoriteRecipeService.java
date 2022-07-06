package com.berg.service;

import com.berg.dto.favorite.FavoriteRecipeCreateDto;
import com.berg.dto.favorite.FavoriteRecipeReadDto;
import com.berg.mapper.favorite.FavoriteRecipeCreateDtoToFavoriteRecipeMapper;
import com.berg.mapper.favorite.FavoriteRecipeToReadDtoMapper;
import com.berg.repositary.FavoriteRecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FavoriteRecipeService {

    private final FavoriteRecipeRepository favoriteRecipeRepository;
    private final FavoriteRecipeToReadDtoMapper favoriteRecipeToReadDtoMapper;
    private final FavoriteRecipeCreateDtoToFavoriteRecipeMapper favoriteRecipeCreateDtoToFavoriteRecipeMapper;

    public List<FavoriteRecipeReadDto> findAll() {
        return favoriteRecipeRepository.findAll().stream()
                .map(favoriteRecipeToReadDtoMapper::map)
                .toList();
    }

    public Optional<FavoriteRecipeReadDto> findById(Long id) {
        return favoriteRecipeRepository.findById(id)
                .map(favoriteRecipeToReadDtoMapper::map);
    }

    @Transactional
    public FavoriteRecipeReadDto create(FavoriteRecipeCreateDto createDto) {
        return Optional.of(createDto)
                .map(favoriteRecipeCreateDtoToFavoriteRecipeMapper::map)
                .map(favoriteRecipeRepository::saveAndFlush)
                .map(favoriteRecipeToReadDtoMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<FavoriteRecipeReadDto> update(Long id, FavoriteRecipeCreateDto favoriteRecipeCreateDto) {
        return favoriteRecipeRepository.findById(id)
                .map(entity -> favoriteRecipeCreateDtoToFavoriteRecipeMapper.map(favoriteRecipeCreateDto, entity))
                .map(favoriteRecipeRepository::saveAndFlush)
                .map(favoriteRecipeToReadDtoMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return favoriteRecipeRepository.findById(id)
                .map(dailyMenu -> {
                    favoriteRecipeRepository.delete(dailyMenu);
                    favoriteRecipeRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
