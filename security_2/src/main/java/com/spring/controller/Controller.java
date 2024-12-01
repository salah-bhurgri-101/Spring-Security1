package com.spring.controller;

import com.spring.dto.UserDto;
import com.spring.exception.HttpRequestMethodNotSupportedException;
import com.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/user")
public class Controller {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok().body(userService.addUser(userDto));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll(){
        List<UserDto> allUser = userService.getAllUser();
        return ResponseEntity.ok().body(allUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById (@PathVariable Long id){
        UserDto byId = userService.getById(id);
        return ResponseEntity.ok().body(byId);
    }
    @PutMapping("/")
    public HttpRequestMethodNotSupportedException updateUrlnotFound(){
        throw new HttpRequestMethodNotSupportedException("url");
    }
}
