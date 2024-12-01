package com.spring.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
public class AddressDto {

    private Long address_id;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    @JsonIgnore
    private UserDto user;
}
