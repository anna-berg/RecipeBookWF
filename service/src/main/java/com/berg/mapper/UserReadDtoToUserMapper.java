package com.berg.mapper;

import com.berg.dto.UserReadDto;
import com.berg.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReadDtoToUserMapper implements Mapper<UserReadDto, User> {

    @Override
    public User map(UserReadDto object) {
        return User.builder()
                .id(object.getId())
                .name(object.getName())
                .email(object.getEmail())
                .password(object.getPassword())
                .role(object.getRole())
                .gender(object.getGender())
                .build();
    }
}
