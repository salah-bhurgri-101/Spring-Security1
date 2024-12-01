package com.spring.dto;

import lombok.*;

import java.util.List;

@Data
public class UserDto {
    private Long user_id;
    private String name;
    private String email;
    private String password;
    private AddressDto address;
}
