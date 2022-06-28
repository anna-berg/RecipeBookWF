package com.berg.mapper;

import com.berg.dto.DailyMenuReadDto;
import com.berg.entity.DailyMenu;
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
