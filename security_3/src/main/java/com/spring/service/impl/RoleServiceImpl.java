package com.spring.service.impl;

import com.spring.modal.Role;
import com.spring.repository.RoleRepo;
import com.spring.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public List<Role> saveAll(List<Role> roles) {
        List<Role> roles1 = roleRepo.saveAll(roles);
        return roles1;
    }
}
