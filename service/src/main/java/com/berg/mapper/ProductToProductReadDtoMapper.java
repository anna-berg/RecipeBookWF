package com.berg.mapper;

import com.berg.dto.ProductReadDto;
import com.berg.entity.Product;
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
