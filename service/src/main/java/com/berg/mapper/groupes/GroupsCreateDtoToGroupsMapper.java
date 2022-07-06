package com.berg.mapper.groupes;

import com.berg.dto.group.GroupsCreateDto;
import com.berg.entity.Groups;
import com.berg.mapper.Mapper;
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
