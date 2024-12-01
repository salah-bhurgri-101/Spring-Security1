package com.spring.repository;

import com.spring.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User , Long> {
    User findByEmail(String email);
}
