package com.berg.mapper;

import com.berg.dto.UserReadDto;
import com.berg.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserToReadDtoMapper implements Mapper<User, UserReadDto> {

    private final FavoriteRecipeToDtoForUserMapper favoriteRecipeToDtoForUserMapper;

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
