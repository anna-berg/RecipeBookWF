package com.berg.mapper.product;

import com.berg.dto.product.ProductDtoForRecipe;
import com.berg.entity.Product;
import com.berg.mapper.Mapper;
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
