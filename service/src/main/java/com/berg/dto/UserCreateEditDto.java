package com.berg.dto;

import com.berg.entity.Gender;
import com.berg.entity.Role;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Value
public class UserCreateEditDto {

    @NotNull
    String name;

    @Email
    String email;

    String password;

    Role role;

    Gender gender;
}