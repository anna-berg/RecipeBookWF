package com.berg.mapper;

import com.berg.dto.ProductDtoForRecipe;
import com.berg.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductDtoForRecipe implements Mapper<Product, ProductDtoForRecipe> {

    @Override
    public ProductDtoForRecipe map(Product object) {
        return new ProductDtoForRecipe(object.getId(),
                object.getName(),
                object.getType());
    }
}
