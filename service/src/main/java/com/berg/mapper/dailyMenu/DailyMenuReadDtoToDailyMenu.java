package com.berg.mapper.dailyMenu;

import com.berg.dto.dailyMenu.DailyMenuReadDto;
import com.berg.entity.DailyMenu;
import com.berg.mapper.Mapper;
import com.berg.mapper.recipe.RecipeReadDtoToRecipeMapper;
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
