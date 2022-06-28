package com.berg.service;

import com.berg.dto.GroupDayReadDto;
import com.berg.mapper.GroupDayReadDtoToGroupDay;
import com.berg.mapper.GroupDayToGroupDayReadDto;
import com.berg.repositary.GroupDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupDayService {

    private final GroupDayRepository groupDayRepository;
    private final GroupDayToGroupDayReadDto groupDayToGroupDayReadDto;
    private final GroupDayReadDtoToGroupDay groupDayReadDtoToGroupDay;

    public List<GroupDayReadDto> findAll() {
        return groupDayRepository.findAll().stream()
                .map(groupDayToGroupDayReadDto::map)
                .toList();
    }

    public Optional<GroupDayReadDto> findById(Long id) {
        return groupDayRepository.findById(id)
                .map(groupDayToGroupDayReadDto::map);
    }

    @Transactional
    public Optional<GroupDayReadDto> update(Long id, GroupDayReadDto groupDayReadDto) {
        return groupDayRepository.findById(id)
                .map(entity -> groupDayReadDtoToGroupDay.map(groupDayReadDto, entity))
                .map(groupDayRepository::saveAndFlush)
                .map(groupDayToGroupDayReadDto::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return groupDayRepository.findById(id)
                .map(dailyMenu -> {
                    groupDayRepository.delete(dailyMenu);
                    groupDayRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
