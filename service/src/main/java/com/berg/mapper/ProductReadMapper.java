package com.berg.mapper;

import com.berg.dto.ProductReadDto;
import com.berg.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductReadMapper implements Mapper<Product, ProductReadDto> {

    @Override
    public ProductReadDto map(Product object) {
        return new ProductReadDto(object.getId(),
                object.getName(),
                object.getProteins(),
                object.getFats(),
                object.getCarbohydrates(),
                object.getType(),
                object.getRecipes());
    }
}
