package com.slavamashkov.springboot_test.services;


import com.slavamashkov.springboot_test.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
}
