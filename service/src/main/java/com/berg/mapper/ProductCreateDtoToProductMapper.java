package com.berg.mapper;

import com.berg.dto.ProductCreateDto;
import com.berg.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductCreateDtoToProductMapper implements Mapper<ProductCreateDto, Product> {

    @Override
    public Product map(ProductCreateDto object) {
        return Product.builder()
                .name(object.getName())
                .carbohydrates(object.getCarbohydrates())
                .fats(object.getFats())
                .proteins(object.getProteins())
                .type(object.getType())
                .build();
    }
}
