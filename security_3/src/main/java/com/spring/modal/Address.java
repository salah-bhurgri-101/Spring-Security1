package com.spring.modal;

import jakarta.persistence.*;
import lombok.*;

@Entity
//@Data -----  tostring() is stack over flow problem that way getter() and setter
// now using own toString() which have hash_code and class name not any field data;
@Getter
@Setter
@RequiredArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long address_id;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    @ManyToOne
    @JoinColumn(name = "users_id",referencedColumnName = "user_id")
    private User user;
}
