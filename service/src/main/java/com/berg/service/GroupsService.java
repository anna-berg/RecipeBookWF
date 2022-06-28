package com.berg.service;

import com.berg.dto.GroupsCreateDto;
import com.berg.dto.GroupsReadDto;
import com.berg.mapper.GroupsCreateDtoToGroupsMapper;
import com.berg.mapper.GroupsToGroupsReadDtoMapper;
import com.berg.repositary.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupsService {

    private final GroupRepository groupRepository;
    private final GroupsToGroupsReadDtoMapper groupsToGroupsReadDtoMapper;
    private final GroupsCreateDtoToGroupsMapper groupsCreateDtoToGroupsMapper;
    public List<GroupsReadDto> findAll() {
        return groupRepository.findAll().stream()
                .map(groupsToGroupsReadDtoMapper::map)
                .collect(toList());
    }

    public Optional<GroupsReadDto> findById(Long id) {
        return groupRepository.findById(id)
                .map(groupsToGroupsReadDtoMapper::map);
    }

    @Transactional
    public GroupsReadDto create(GroupsCreateDto createDto) {
        return Optional.of(createDto)
                .map(groupsCreateDtoToGroupsMapper::map)
                .map(groupRepository::save)
                .map(groupsToGroupsReadDtoMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<GroupsReadDto> update(Long id, GroupsCreateDto groupsCreateDto) {
        return groupRepository.findById(id)
                .map(groups -> groupsCreateDtoToGroupsMapper.map(groupsCreateDto, groups))
                .map(groupRepository::saveAndFlush)
                .map(groupsToGroupsReadDtoMapper::map);
    }

    public boolean delete(Long id) {
        return groupRepository.findById(id)
                .map(groups -> {
                    groupRepository.delete(groups);
                    groupRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
