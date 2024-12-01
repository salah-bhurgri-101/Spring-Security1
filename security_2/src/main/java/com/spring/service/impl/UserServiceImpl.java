package com.spring.service.impl;

import com.spring.dto.UserDto;
import com.spring.exception.ResourceNotFoundException;
import com.spring.modal.User;
import com.spring.repository.UserRepo;
import com.spring.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto addUser(UserDto userDto) {
        if(userRepo.findByEmail(userDto.getEmail()) != null){
            throw new RuntimeException("Record already exists");
        }
        userDto.getAddress().setUser(userDto);
        return  modelMapper.map(userRepo.save(modelMapper.map(userDto, User.class)), UserDto.class);
    }

    @Override
    public UserDto getById(Long id) {
        return modelMapper.map(userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "Id", id)), UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        return userRepo.findAll()
                .stream()
                .map(user -> modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());
    }
}
