package com.berg.mapper;

import com.berg.dto.UserReadDto;
import com.berg.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {
    @Override
    public UserReadDto map(User object) {
        return new UserReadDto(object.getId(),
                object.getName(),
                object.getEmail(),
                object.getPassword(),
                object.getRole(),
                object.getGender(),
                object.getFavoriteRecipes(),
                object.getGroups());
    }
}
