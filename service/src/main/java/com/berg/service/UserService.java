package com.berg.service;

import com.berg.dto.user.UserCreateEditDto;
import com.berg.dto.user.UserPrincipleDto;
import com.berg.dto.user.UserReadDto;
import com.berg.entity.Role;
import com.berg.mapper.user.UserCreateEditToUserMapper;
import com.berg.mapper.user.UserToReadDtoMapper;
import com.berg.repositary.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserToReadDtoMapper userToReadDtoMapper;
    private final UserCreateEditToUserMapper userCreateEditToUserMapper;

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userToReadDtoMapper::map)
                .toList();
    }

    public Optional<UserReadDto> findById(Long id) {
        var loggedInUser = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(authentication -> (UserPrincipleDto) authentication.getPrincipal())
                .orElseThrow();

        return userRepository.findById(id)
                .filter(user ->
                        loggedInUser.getAuthorities().contains(Role.ADMIN) ||
                                loggedInUser.getUsername().equals(user.getCreatedBy()))
                .map(userToReadDtoMapper::map);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(userCreateEditToUserMapper::map)
                .map(userRepository::saveAndFlush)
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @Override
    public UserPrincipleDto loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findAllByEmail(username)
                .map(user -> new UserPrincipleDto(
                        user.getId(),
                        user.getEmail(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }
}
