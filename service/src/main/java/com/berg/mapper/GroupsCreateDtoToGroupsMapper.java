package com.berg.mapper;

import com.berg.dto.GroupsCreateDto;
import com.berg.entity.Groups;
import org.springframework.stereotype.Component;

@Component
public class GroupsCreateDtoToGroupsMapper implements Mapper<GroupsCreateDto, Groups> {

    Groups group = new Groups();

    @Override
    public Groups map(GroupsCreateDto fromObject, Groups toObject) {
        toObject.setGroupTitle(fromObject.getGroupTitle());
        return toObject;
    }

    @Override
    public Groups map(GroupsCreateDto object) {
        group.setGroupTitle(object.getGroupTitle());
        return group;
    }
}
