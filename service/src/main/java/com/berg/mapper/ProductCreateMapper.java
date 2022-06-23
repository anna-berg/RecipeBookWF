package com.berg.mapper;

import com.berg.dto.ProductCreateDto;
import com.berg.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductCreateMapper implements Mapper<ProductCreateDto, Product> {

    @Override
    public Product map(ProductCreateDto object) {
        return Product.builder()
                .name(object.name())
                .carbohydrates(object.carbohydrates())
                .fats(object.fats())
                .proteins(object.proteins())
                .type(object.type())
                .build();
    }
}
