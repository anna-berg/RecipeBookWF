package com.berg.mapper;

import com.berg.dto.UserCreateDto;
import com.berg.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserCreateMapper implements Mapper<UserCreateDto, User> {
    @Override
    public User map(UserCreateDto object) {
        return User.builder()
                .name(object.name())
                .build();
    }
}