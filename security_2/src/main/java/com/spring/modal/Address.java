package com.spring.modal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "n_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long address_id;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    @OneToOne
    @JoinColumn(name = "u_id" , referencedColumnName = "user_id")
    private User user;
}
