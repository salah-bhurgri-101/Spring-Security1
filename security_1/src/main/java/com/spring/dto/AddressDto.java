package com.spring.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

//@Data -----  tostring() is stack over flow problem that way getter() and setter
// now using own toString() which have hash_code and class name not any field data;
@Getter
@Setter
@RequiredArgsConstructor
public class AddressDto {

    private Long address_id;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    @JsonIgnore
    private UserDto user;
}
