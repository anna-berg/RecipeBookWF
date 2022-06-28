package com.berg.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class AuthorCreateEditDto {

    @NotNull
    String name;
}
