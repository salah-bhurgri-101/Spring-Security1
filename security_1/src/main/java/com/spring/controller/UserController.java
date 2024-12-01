package com.spring.controller;

import com.spring.dto.UserDto;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/home/normal")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping
    public ResponseEntity<UserDto> getUser(Principal principal){
        String username = principal.getName();
        UserDto user = userService.findByName(username);
        return new ResponseEntity<UserDto>(user , HttpStatus.FOUND);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto>  updateUser(@RequestBody UserDto userDto ,@PathVariable Long userId){
//        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getName(), userDto.getPassword()));
//        if(authenticate.isAuthenticated()){
            return ResponseEntity.ok(userService.updateUser(userDto, userId));
//        }else {
//            throw new RuntimeException("username and password not valid");
//        }
    }

}
