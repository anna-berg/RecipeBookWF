package com.berg.dto;

import com.berg.entity.Gender;
import com.berg.entity.Role;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class UserReadDto {

    Long id;
    String name;
    String email;
    String password;
    Role role;
    Gender gender;
}
