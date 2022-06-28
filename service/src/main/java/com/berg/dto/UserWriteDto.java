package com.berg.dto;

import com.berg.entity.Gender;
import com.berg.entity.Role;
import lombok.Value;

@Value
public class UserWriteDto {

    String name;
    String email;
    String password;
    Role role;
    Gender gender;
}
