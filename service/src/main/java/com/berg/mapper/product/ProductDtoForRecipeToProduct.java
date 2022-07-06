package com.berg.mapper.product;

import com.berg.dto.product.ProductDtoForRecipe;
import com.berg.entity.Product;
import com.berg.mapper.Mapper;
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
