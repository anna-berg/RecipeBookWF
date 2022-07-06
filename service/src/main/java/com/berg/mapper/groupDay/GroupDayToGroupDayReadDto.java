package com.berg.mapper.groupDay;

import com.berg.dto.GroupDayReadDto;
import com.berg.entity.GroupDay;
import com.berg.mapper.groupes.GroupesToGroupsReadDto;
import com.berg.mapper.Mapper;
import com.berg.mapper.dailyMenu.DailyMenuToDailyMenuReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupDayToGroupDayReadDto implements Mapper<GroupDay, GroupDayReadDto> {

    private final GroupesToGroupsReadDto groupesToGroupsReadDto;
    private final DailyMenuToDailyMenuReadDto dailyMenuToDailyMenuReadDto;

    @Override
    public GroupDayReadDto map(GroupDay object) {
        return new GroupDayReadDto(
                object.getId(),
                groupesToGroupsReadDto.map(object.getGroup()),
                dailyMenuToDailyMenuReadDto.map(object.getDailyMenu()),
                object.getPosition()
        );
    }
}
