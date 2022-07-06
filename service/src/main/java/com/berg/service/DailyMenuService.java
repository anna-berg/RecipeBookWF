package com.berg.service;

import com.berg.dto.dailyMenu.DailyMenuCreateDto;
import com.berg.dto.dailyMenu.DailyMenuReadDto;
import com.berg.mapper.dailyMenu.DailyMenuCreateDtoToDailyMenuMapper;
import com.berg.mapper.dailyMenu.DailyMenuToDailyMenuReadDto;
import com.berg.repositary.DailyMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DailyMenuService {

    private final DailyMenuRepository dailyMenuRepository;
    private final DailyMenuToDailyMenuReadDto dailyMenuToDailyMenuReadDto;
    private final DailyMenuCreateDtoToDailyMenuMapper dailyMenuCreateDtoToDailyMenuMapper;

    public List<DailyMenuReadDto> findAll() {
        return dailyMenuRepository.findAll().stream()
                .map(dailyMenuToDailyMenuReadDto::map)
                .toList();
    }

    public Optional<DailyMenuReadDto> findById(Long id) {
        return dailyMenuRepository.findById(id)
                .map(dailyMenuToDailyMenuReadDto::map);
    }

    @Transactional
    public DailyMenuReadDto create(DailyMenuCreateDto createDto) {
        return Optional.of(createDto)
                .map(dailyMenuCreateDtoToDailyMenuMapper::map)
                .map(dailyMenuRepository::saveAndFlush)
                .map(dailyMenuToDailyMenuReadDto::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<DailyMenuReadDto> update(Long id, DailyMenuCreateDto dailyMenuCreateDto) {
        return dailyMenuRepository.findById(id)
                .map(dailyMenu -> dailyMenuCreateDtoToDailyMenuMapper.map(dailyMenuCreateDto, dailyMenu))
                .map(dailyMenuRepository::saveAndFlush)
                .map(dailyMenuToDailyMenuReadDto::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return dailyMenuRepository.findById(id)
                .map(dailyMenu -> {
                    dailyMenuRepository.delete(dailyMenu);
                    dailyMenuRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
