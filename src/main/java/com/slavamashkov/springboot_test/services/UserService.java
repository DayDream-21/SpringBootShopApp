package com.slavamashkov.springboot_test.services;

import com.slavamashkov.springboot_test.entities.Role;
import com.slavamashkov.springboot_test.entities.User;
import com.slavamashkov.springboot_test.exceptions.UserAlreadyExistsException;
import com.slavamashkov.springboot_test.repositories.RoleRepository;
import com.slavamashkov.springboot_test.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        mapRolesToAuthorities(user.getRoles())
                )).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }

    private void saveUser(User user) {
        userRepository.save(user);
    }

    public void registerUser(User user) throws UserAlreadyExistsException {
        if (usernameExists(user.getUsername())) {
            throw new UserAlreadyExistsException("There is an account with that username: " + user.getUsername());
        }

        if (emailExists(user.getEmail())) {
            throw new UserAlreadyExistsException("There is an account with that email address: " + user.getEmail());
        }

        User newUser = User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .name(user.getName())
                .email(user.getEmail())
                .enabled(Boolean.TRUE)
                .roles(roleRepository.findAllById(List.of(1L)))
                .build();

        saveUser(newUser);
    }

    private boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

}
