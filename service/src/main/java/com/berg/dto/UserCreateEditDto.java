package com.berg.dto;

import com.berg.entity.Gender;
import com.berg.entity.Role;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
public class UserCreateEditDto {
    String name;
    String email;
    String password;
    Role role;
    Gender gender;
}