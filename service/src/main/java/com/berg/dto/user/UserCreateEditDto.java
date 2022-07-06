package com.berg.dto.user;

import com.berg.entity.Gender;
import com.berg.entity.Role;
import com.berg.validation.group.CreateAction;
import lombok.Value;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@Validated
public class UserCreateEditDto {

    @NotNull
    String name;

    @Email (message = "Email is wrong")
    String email;

    @NotBlank(groups = CreateAction.class)
    String rawPassword;

    Role role;

    Gender gender;
}