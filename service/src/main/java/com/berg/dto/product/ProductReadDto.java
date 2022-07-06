package com.berg.dto.product;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class ProductReadDto {

    Long id;
    String name;
    Integer proteins;
    Integer fats;
    Integer carbohydrates;
    String type;
}
