package com.example.registerlogin;

import com.example.registerlogin.entities.Role;
import com.example.registerlogin.repositorys.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RegisterLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterLoginApplication.class, args);
    }
    @Bean
    public CommandLineRunner demo(RoleRepository roleRepo) {
        return (args) -> {
            Role role=new Role();
            role.setName("ROLE_ADMIN");
            roleRepo.save(role);
        };
    }
}
