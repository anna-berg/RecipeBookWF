package com.berg.dto;

import lombok.Value;

@Value
public class GroupsReadDto {

    Long id;
    UserReadDto user;
    String groupTitle;
}
