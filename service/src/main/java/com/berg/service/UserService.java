package com.berg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

//    private final UserRepository userRepository;
//    private final UserCreateMapper userCreateMapper;
//    private final UserReadMapper userReadMapper;
//
//    public Long create(UserCreateDto userDto) {
//        var userEntity = userCreateMapper.mapFrom(userDto);
//        return userRepository.save(userEntity).getId();
//    }
//
//    public Optional<UserReadDto> findById(Long id) {
//        return userRepository.findById(id)
//                .map(userReadMapper::mapFrom);
//    }
//
//    public boolean delete(Long id) {
//        var maybeUser = userRepository.findById(id);
//        maybeUser.ifPresent(user -> userRepository.delete(id));
//        return maybeUser.isPresent();
//    }
//
//    public void update(User user) {
//        userRepository.update(user);
//    }
//
//    public List<UserReadDto> findAll() {
//        return userRepository.findAll().stream()
//                .map(userReadMapper::mapFrom)
//                .collect(toList());
//    }
}
