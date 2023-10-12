package com.example.registerlogin.repositorys;

import com.example.registerlogin.entities.Role;
import com.example.registerlogin.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReposiitory extends JpaRepository<User, Integer> {
    Optional<User> findUsersByEmailOrUserName(String email, String userName);
    boolean existsByUserName(String username);

    boolean existsByEmail(String email);
}
