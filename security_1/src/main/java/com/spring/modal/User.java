package com.spring.modal;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//@Data -----  tostring() is stack over flow problem that way getter() and setter
// now using own toString() which have hash_code and class name not any field data;
@Getter
@Setter
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> addresses;

    @ManyToMany(cascade = CascadeType.ALL  ,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role" , joinColumns = @JoinColumn(name ="userid" , referencedColumnName = "user_id"),inverseJoinColumns = @JoinColumn(name = "roleid" , referencedColumnName = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
