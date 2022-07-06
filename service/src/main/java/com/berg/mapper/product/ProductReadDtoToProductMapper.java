package com.berg.mapper.product;

import com.berg.dto.product.ProductReadDto;
import com.berg.entity.Product;
import com.berg.mapper.Mapper;
import com.berg.mapper.recipe.RecipeReadDtoToRecipeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductReadDtoToProductMapper implements Mapper<ProductReadDto, Product> {

    private final RecipeReadDtoToRecipeMapper recipeReadDtoToRecipeMapper;

    @Override
    public Product map(ProductReadDto object) {
        return Product.builder()
                .id(object.getId())
                .name(object.getName())
                .proteins(object.getProteins())
                .fats(object.getFats())
                .carbohydrates(object.getCarbohydrates())
                .type(object.getType())
                .build();
    }
}
