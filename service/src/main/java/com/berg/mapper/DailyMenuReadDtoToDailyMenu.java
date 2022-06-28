package com.berg.mapper;

import com.berg.dto.DailyMenuReadDto;
import com.berg.entity.DailyMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DailyMenuReadDtoToDailyMenu implements Mapper<DailyMenuReadDto, DailyMenu> {

    private final RecipeReadDtoToRecipeMapper recipeReadDtoToRecipeMapper;

    @Override
    public DailyMenu map(DailyMenuReadDto object) {
        return DailyMenu.builder()
                .id(object.getId())
                .breakfast(recipeReadDtoToRecipeMapper.map(object.getBreakfast()))
                .firstSnack(recipeReadDtoToRecipeMapper.map(object.getFirstSnack()))
                .lunch(recipeReadDtoToRecipeMapper.map(object.getLunch()))
                .secondSnack(recipeReadDtoToRecipeMapper.map(object.getSecondSnack()))
                .dinner(recipeReadDtoToRecipeMapper.map(object.getDinner()))
                .title(object.getTitle())
                .build();
    }
}
