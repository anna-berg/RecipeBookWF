package com.berg.mapper;

import com.berg.dto.GroupsReadDto;
import com.berg.entity.Groups;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupsToGroupsReadDtoMapper implements Mapper<Groups, GroupsReadDto> {

    private final UserToReadDtoMapper userToReadDtoMapper;

    @Override
    public GroupsReadDto map(Groups object) {
        return new GroupsReadDto(
                object.getId(),
                userToReadDtoMapper.map(object.getUser()),
                object.getGroupTitle()
        );
    }
}
