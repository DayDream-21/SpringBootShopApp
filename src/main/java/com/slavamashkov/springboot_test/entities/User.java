package com.slavamashkov.springboot_test.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "spring_boot_schema")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Email(message = "Email should be valid: [name]@gmail.com")
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = false;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User(String username, String password, String name, String email, Boolean enabled, Collection<Role> roles) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.enabled = enabled;
        this.roles = roles;
    }
}