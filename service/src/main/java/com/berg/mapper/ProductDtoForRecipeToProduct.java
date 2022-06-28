package com.berg.mapper;

import com.berg.dto.ProductDtoForRecipe;
import com.berg.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoForRecipeToProduct implements Mapper<ProductDtoForRecipe, Product> {

    @Override
    public Product map(ProductDtoForRecipe object) {
        return Product.builder()
                .id(object.getId())
                .name(object.getName())
                .type(object.getType())
                .build();
    }
}
