package com.berg.mapper.groupDay;

import com.berg.dto.GroupDayReadDto;
import com.berg.entity.GroupDay;
import com.berg.mapper.groupes.GroupesReadDtoToGroups;
import com.berg.mapper.Mapper;
import com.berg.mapper.dailyMenu.DailyMenuReadDtoToDailyMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupDayReadDtoToGroupDay implements Mapper<GroupDayReadDto, GroupDay> {

    private final GroupesReadDtoToGroups groupesReadDtoToGroups;
    private final DailyMenuReadDtoToDailyMenu dailyMenuReadDtoToDailyMenu;

    @Override
    public GroupDay map(GroupDayReadDto fromObject, GroupDay toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public GroupDay map(GroupDayReadDto object) {
        GroupDay groupDay = new GroupDay();
        groupDay.setId(object.getId());
        copy(object, groupDay);
        return groupDay;
    }

    private void copy(GroupDayReadDto object, GroupDay groupDay) {
        groupDay.setGroup(groupesReadDtoToGroups.map(object.getGroup()));
        groupDay.setDailyMenu(dailyMenuReadDtoToDailyMenu.map(object.getDailyMenu()));
        groupDay.setPosition(object.getPosition());
    }
}
