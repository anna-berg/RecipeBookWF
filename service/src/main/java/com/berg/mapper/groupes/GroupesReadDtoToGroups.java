package com.berg.mapper.groupes;

import com.berg.dto.group.GroupsReadDto;
import com.berg.entity.Groups;
import com.berg.mapper.Mapper;
import com.berg.mapper.user.UserReadDtoToUserMapper;
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
