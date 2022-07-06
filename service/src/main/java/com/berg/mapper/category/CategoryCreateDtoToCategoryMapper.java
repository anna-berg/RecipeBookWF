package com.berg.mapper.category;

import com.berg.dto.category.CategoryCreateDto;
import com.berg.entity.CategoryRecipe;
import com.berg.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryCreateDtoToCategoryMapper implements Mapper<CategoryCreateDto, CategoryRecipe> {

    @Override
    public CategoryRecipe map(CategoryCreateDto object) {
        CategoryRecipe category = new CategoryRecipe();
        copy(object, category);
        return category;
    }

    @Override
    public CategoryRecipe map(CategoryCreateDto fromObject, CategoryRecipe toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(CategoryCreateDto object, CategoryRecipe categoryRecipe){
        categoryRecipe.setCategory(object.getCategory());
    }
}
