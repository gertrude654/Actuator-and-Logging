package com.example.LoggingAndActuator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        // Actuator endpoints access control
                        .requestMatchers("/actuator/health", "/actuator/info").permitAll()  // Accessible to everyone
                        .requestMatchers("/actuator/metrics/**").hasAnyRole("USER", "ADMIN")  // Accessible by 'USER' or 'ADMIN' role
                        .requestMatchers("/actuator/**").hasRole("ADMIN")  // Only accessible by 'ADMIN' role
                        .anyRequest().authenticated()  // All other endpoints require authentication
                )
                .httpBasic();  // Using Basic Authentication
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Define multiple users with different roles
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin123")
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password("{noop}user123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
