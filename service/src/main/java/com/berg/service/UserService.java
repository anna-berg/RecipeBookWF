package com.berg.service;

import com.berg.dto.UserCreateEditDto;
import com.berg.dto.UserReadDto;
import com.berg.mapper.UserCreateEditToUserMapper;
import com.berg.mapper.UserToReadDtoMapper;
import com.berg.repositary.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserToReadDtoMapper userToReadDtoMapper;
    private final UserCreateEditToUserMapper userCreateEditToUserMapper;

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userToReadDtoMapper::map)
                .toList();
    }

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userToReadDtoMapper::map);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(userCreateEditToUserMapper::map)
                .map(userRepository::save)
                .map(userToReadDtoMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto userDto) {
        return userRepository.findById(id)
                .map(entity -> userCreateEditToUserMapper.map(userDto, entity))
                .map(userRepository::saveAndFlush)
                .map(userToReadDtoMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
