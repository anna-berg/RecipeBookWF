package com.berg.service.service;

import com.berg.service.dao.UserDao;
import com.berg.service.dto.UserDto;

import java.util.Optional;

public class UserService {

    private final UserDao userDao = UserDao.getInstance();

}
