package com.spring;

import com.spring.constant.AppConstants;
import com.spring.modal.Role;
import com.spring.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityApplication implements CommandLineRunner {

	@Autowired
	private RoleService roleService;


	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {

			Role role = new Role();
			role.setRole_id(AppConstants.ADMIN_USER);
			role.setName("ROLE_ADMIN");

			Role role2 = new Role();
			role2.setRole_id(AppConstants.NORMAL_USER);
			role2.setName("ROLE_NORMAL");


			java.util.List<Role> roles = new ArrayList<>();
			roles.add(role);
			roles.add(role2);

			java.util.List<Role> saveAll = roleService.saveAll(roles);

			saveAll.forEach(r->{
				System.out.println(r.getName());
			});


		} catch (Exception e) {
			// TODO: handle exception

		}
	}
}
