package com.spring.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Role {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long role_id;
    private String name;



}
