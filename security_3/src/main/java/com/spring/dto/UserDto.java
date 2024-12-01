package com.spring.dto;

import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Data -----  tostring() is stack over flow problem that way getter() and setter
// now using own toString() which have hash_code and class name not any field data;
@Getter
@Setter
@RequiredArgsConstructor
public class UserDto {
//    private Long user_id;
    private String name;
    private String email;
    private String password;
    private List<AddressDto> addresses;
    private Set<RoleDto> roles = new HashSet<>();
}
