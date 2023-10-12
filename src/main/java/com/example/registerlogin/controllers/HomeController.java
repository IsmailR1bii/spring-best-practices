package com.example.registerlogin.controllers;

import com.example.registerlogin.dto.LoginDTO;
import com.example.registerlogin.dto.SignUpDTO;
import com.example.registerlogin.entities.Role;
import com.example.registerlogin.entities.User;
import com.example.registerlogin.repositorys.RoleRepository;
import com.example.registerlogin.repositorys.UserReposiitory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Security;
import java.util.Collections;

@RestController("/api")
@AllArgsConstructor
public class HomeController {
    private final AuthenticationManager authenticationManager;
    private final UserReposiitory userReposiitory;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("logged successfully ", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDTO signUpDTO){
        if (userReposiitory.existsByUserName(signUpDTO.getUsername())){
            return new ResponseEntity<>("UserName already exits", HttpStatus.BAD_GATEWAY);
        }
        if (userReposiitory.existsByEmail(signUpDTO.getEmail())){
            return new ResponseEntity<>("Email already exits", HttpStatus.BAD_REQUEST);
        }

        Role role = roleRepository.findRoleByName("ROLE_ADMIN").orElse(null);
        if (role == null){
            throw new RuntimeException("check ur roles");
        }
        User user = User.builder()
                .userName(signUpDTO.getUsername())
                .name(signUpDTO.getName())
                .password(passwordEncoder.encode(signUpDTO.getPassword()))
                .roles(Collections.singleton(role))
                .build();
        userReposiitory.save(user);
        return new ResponseEntity<>("user is registered successfully", HttpStatus.OK);
    }

}
