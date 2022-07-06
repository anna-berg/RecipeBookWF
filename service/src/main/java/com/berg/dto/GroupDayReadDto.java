package com.berg.dto;

import com.berg.dto.dailyMenu.DailyMenuReadDto;
import com.berg.dto.group.GroupsReadDto;
import lombok.Value;

@Value
public class GroupDayReadDto {

    Long id;
    GroupsReadDto group;
    DailyMenuReadDto dailyMenu;
    int position;
}
