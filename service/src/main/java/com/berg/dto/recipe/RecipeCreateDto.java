package com.berg.dto.recipe;

import lombok.Value;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Value
@Validated
public class RecipeCreateDto {

    @NotNull
    String title;

    Long authorId;

    @NotNull
    String description;
    String measure;

    @NotNull
    Long categoryId;

    @NotNull
    List<Long> products;
}
