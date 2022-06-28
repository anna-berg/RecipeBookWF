package com.berg.dto;

import lombok.Value;

@Value
public class GroupDayReadDto {

    Long id;
    GroupsReadDto group;
    DailyMenuReadDto dailyMenu;
    int position;
}
