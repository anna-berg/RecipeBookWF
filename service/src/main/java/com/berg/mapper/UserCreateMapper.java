package com.berg.mapper;

import com.berg.dto.UserCreateDto;
import com.berg.entity.User;

public class UserCreateMapper implements Mapper<UserCreateDto, User> {
    @Override
    public User mapFrom(UserCreateDto object) {
        return User.builder()
                .name(object.name())
                .build();
    }
}