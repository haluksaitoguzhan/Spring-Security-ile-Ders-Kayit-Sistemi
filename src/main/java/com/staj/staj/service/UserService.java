package com.staj.staj.service;

import com.staj.staj.model.User;
import com.staj.staj.model.UserDto;

import java.util.List;

public interface UserService {
    User save(UserDto user);
    List<User> findAll();
    User findOneById(Long id);
}
