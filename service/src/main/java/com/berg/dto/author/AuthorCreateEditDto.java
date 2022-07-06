package com.berg.dto.author;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class AuthorCreateEditDto {

    @NotNull
    String name;
}
