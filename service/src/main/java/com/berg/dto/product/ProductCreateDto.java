package com.berg.dto.product;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class ProductCreateDto {

    @NotNull
    String name;

    Integer proteins;
    Integer fats;
    Integer carbohydrates;

    @NotNull
    String type;
}
