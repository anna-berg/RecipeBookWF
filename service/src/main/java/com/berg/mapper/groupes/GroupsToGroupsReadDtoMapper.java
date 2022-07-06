package com.berg.mapper.groupes;

import com.berg.dto.group.GroupsReadDto;
import com.berg.entity.Groups;
import com.berg.mapper.Mapper;
import com.berg.mapper.user.UserToReadDtoMapper;
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
