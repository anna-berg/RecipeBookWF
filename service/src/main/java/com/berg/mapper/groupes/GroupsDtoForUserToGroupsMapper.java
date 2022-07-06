package com.berg.mapper.groupes;

import com.berg.dto.group.GroupsReadDto;
import com.berg.entity.Groups;
import com.berg.mapper.Mapper;
import com.berg.mapper.groupDay.GroupDayReadDtoToGroupDay;
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
