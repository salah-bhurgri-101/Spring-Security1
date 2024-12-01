package com.spring.modal;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name = "n_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;
    private String name;
    private String email;
    private String password;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Address address;

}
