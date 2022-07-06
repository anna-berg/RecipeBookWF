package com.berg.mapper.product;

import com.berg.dto.product.ProductCreateDto;
import com.berg.entity.Product;
import com.berg.mapper.Mapper;
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
