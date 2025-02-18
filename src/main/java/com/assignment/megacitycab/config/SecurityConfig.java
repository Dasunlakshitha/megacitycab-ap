package com.assignment.megacitycab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for testing
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home", "/index", "/WEB-INF/views/**").permitAll()  // Allow JSP pages
                        .requestMatchers("/api/**").permitAll()  // Allow API
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login.disable()) // Disable login form
                .httpBasic(basic -> basic.disable()); // Disable basic authentication

        return http.build();
    }
}
