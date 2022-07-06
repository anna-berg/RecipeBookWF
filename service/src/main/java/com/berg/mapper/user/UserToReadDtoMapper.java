package com.berg.mapper.user;

import com.berg.dto.user.UserReadDto;
import com.berg.entity.User;
import com.berg.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserToReadDtoMapper implements Mapper<User, UserReadDto> {

    @Override
    public UserReadDto map(User object) {
        return new UserReadDto(object.getId(),
                object.getName(),
                object.getEmail(),
                object.getPassword(),
                object.getRole(),
                object.getGender()
        );
    }
}
