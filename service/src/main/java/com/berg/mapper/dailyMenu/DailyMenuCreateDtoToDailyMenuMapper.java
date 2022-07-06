package com.berg.mapper.dailyMenu;

import com.berg.dto.dailyMenu.DailyMenuCreateDto;
import com.berg.entity.DailyMenu;
import com.berg.mapper.Mapper;
import com.berg.mapper.recipe.RecipeReadDtoToRecipeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DailyMenuCreateDtoToDailyMenuMapper implements Mapper<DailyMenuCreateDto, DailyMenu> {

    private final RecipeReadDtoToRecipeMapper recipeReadDtoToRecipeMapper;

    @Override
    public DailyMenu map(DailyMenuCreateDto fromObject, DailyMenu toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public DailyMenu map(DailyMenuCreateDto object) {
        DailyMenu dailyMenu = new DailyMenu();
        copy(object, dailyMenu);
        return dailyMenu;
    }

    private void copy(DailyMenuCreateDto object, DailyMenu dailyMenu) {
        dailyMenu.setBreakfast(recipeReadDtoToRecipeMapper.map(object.getBreakfast()));
        dailyMenu.setFirstSnack(recipeReadDtoToRecipeMapper.map(object.getFirstSnack()));
        dailyMenu.setLunch(recipeReadDtoToRecipeMapper.map(object.getLunch()));
        dailyMenu.setSecondSnack(recipeReadDtoToRecipeMapper.map(object.getSecondSnack()));
        dailyMenu.setDinner(recipeReadDtoToRecipeMapper.map(object.getDinner()));
        dailyMenu.setTitle(object.getTitle());
    }
}
