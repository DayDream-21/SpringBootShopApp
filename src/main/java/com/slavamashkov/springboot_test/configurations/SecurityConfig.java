package com.slavamashkov.springboot_test.configurations;

import com.slavamashkov.springboot_test.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authentication/registration").permitAll()
                .antMatchers("/authentication/registerUser").permitAll()
                .antMatchers("/product").hasAnyRole("USER")
                .antMatchers("/product/singleProduct/{id}").hasAnyRole("USER")
                .antMatchers("/product/{page}").hasAnyRole("USER")
                .antMatchers("/product/**").hasAnyRole("ADMIN")
                    .and()
                .formLogin()
                .loginPage("/authentication/login")
                .loginProcessingUrl("/authenticateTheUser")
                .defaultSuccessUrl("/product")
                .permitAll()
                    .and()
                .logout()
                .logoutSuccessUrl("/authentication/login")
                .permitAll();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }
}
