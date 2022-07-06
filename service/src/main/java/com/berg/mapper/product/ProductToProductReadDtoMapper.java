package com.berg.mapper.product;

import com.berg.dto.product.ProductReadDto;
import com.berg.entity.Product;
import com.berg.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductToProductReadDtoMapper implements Mapper<Product, ProductReadDto> {

    @Override
    public ProductReadDto map(Product object) {
        return new ProductReadDto(object.getId(),
                object.getName(),
                object.getProteins(),
                object.getFats(),
                object.getCarbohydrates(),
                object.getType());
    }
}
