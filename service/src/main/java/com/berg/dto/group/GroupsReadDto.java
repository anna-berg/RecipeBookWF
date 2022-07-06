package com.berg.dto.group;

import com.berg.dto.user.UserReadDto;
import lombok.Value;

@Value
public class GroupsReadDto {

    Long id;
    UserReadDto user;
    String groupTitle;
}
