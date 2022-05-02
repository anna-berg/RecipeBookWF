package com.berg.mapper;

import com.berg.dto.ProductReadDto;
import com.berg.entity.Product;

public class ProductReadMapper implements Mapper<Product, ProductReadDto>{

    @Override
    public ProductReadDto mapFrom(Product object) {
        return new ProductReadDto(object.getId(),
                object.getName(),
                object.getProteins(),
                object.getFats(),
                object.getCarbohydrates(),
                object.getType(),
                object.getRecipes());
    }
}