package com.berg.mapper;

import com.berg.dto.GroupsReadDto;
import com.berg.entity.Groups;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupesReadDtoToGroups implements Mapper<GroupsReadDto, Groups> {

    private final UserReadDtoToUserMapper userReadDtoToUserMapper;

    @Override
    public Groups map(GroupsReadDto object) {
        return Groups.builder()
                .id(object.getId())
                .user(userReadDtoToUserMapper.map(object.getUser()))
                .groupTitle(object.getGroupTitle())
                .build();
    }
}
