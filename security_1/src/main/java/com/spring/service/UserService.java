package com.spring.service;

import com.spring.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto addUser (UserDto userDto);

    UserDto getById (Long id);

    List<UserDto> getAllUser();

    UserDto updateUser(UserDto userDto , Long userId);

    UserDto findByName(String username);
}
