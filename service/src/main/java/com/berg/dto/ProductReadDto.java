package com.berg.dto;

import lombok.Value;

@Value
public class ProductReadDto {

    Long id;
    String name;
    Integer proteins;
    Integer fats;
    Integer carbohydrates;
    String type;
}
