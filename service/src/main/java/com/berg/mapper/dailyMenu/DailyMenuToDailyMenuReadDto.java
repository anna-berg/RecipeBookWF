package com.berg.mapper.dailyMenu;

import com.berg.dto.dailyMenu.DailyMenuReadDto;
import com.berg.entity.DailyMenu;
import com.berg.mapper.Mapper;
import com.berg.mapper.recipe.RecipeToRecipeReadDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DailyMenuToDailyMenuReadDto implements Mapper<DailyMenu, DailyMenuReadDto> {

    private final RecipeToRecipeReadDtoMapper recipeToRecipeReadDtoMapper;

    @Override
    public DailyMenuReadDto map(DailyMenu object) {
        return new DailyMenuReadDto(
                object.getId(),
                recipeToRecipeReadDtoMapper.map(object.getBreakfast()),
                recipeToRecipeReadDtoMapper.map(object.getFirstSnack()),
                recipeToRecipeReadDtoMapper.map(object.getLunch()),
                recipeToRecipeReadDtoMapper.map(object.getSecondSnack()),
                recipeToRecipeReadDtoMapper.map(object.getDinner()),
                object.getTitle()
        );
    }
}
