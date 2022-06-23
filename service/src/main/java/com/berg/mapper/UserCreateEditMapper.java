package com.berg.mapper;

import com.berg.dto.UserCreateEditDto;
import com.berg.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper  implements Mapper<UserCreateEditDto, User> {

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();
        copy(object, user);

        return user;
    }

    private void copy(UserCreateEditDto object, User user) {
        user.setEmail(object.getEmail());
        user.setName(object.getName());
        user.setPassword(object.getPassword());
        user.setRole(object.getRole());
        user.setGender(object.getGender());
    }
}
