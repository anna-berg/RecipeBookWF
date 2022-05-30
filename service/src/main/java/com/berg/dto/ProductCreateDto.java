package com.berg.dto;

public record ProductCreateDto(
        String name,
        Integer proteins,
        Integer fats,
        Integer carbohydrates,
        String type) {
}
