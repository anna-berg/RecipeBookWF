package com.berg.mapper;

import com.berg.dto.GroupsReadDto;
import com.berg.entity.Groups;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupsDtoForUserToGroupsMapper implements Mapper<GroupsReadDto, Groups> {

    private final GroupDayReadDtoToGroupDay groupDayReadDtoToGroupDay;

    @Override
    public Groups map(GroupsReadDto object) {
        return Groups.builder()
                .id(object.getId())
                .groupTitle(object.getGroupTitle())
                .build();
    }
}
