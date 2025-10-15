package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    // assign specific roles to specific users in the database
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john = User.builder().username("john").password("{noop}test123").roles("EMPLOYEE").build();
        UserDetails mary = User.builder().username("mary").password("{noop}testmary123").roles("EMPLOYEE", "MANAGER").build();
        UserDetails susan = User.builder().username("susan").password("{noop}testsusan123").roles("EMPLOYEE", "MANAGER", "ADMIN").build();

        return new InMemoryUserDetailsManager(john, susan, mary);
    }

    // restrict access to certain http methods depending on their roles
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer.
                        requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE").
                        requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE").
                        requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER").
                        requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER").
                        requestMatchers(HttpMethod.DELETE, "/api/employees").hasRole("ADMIN").
                        requestMatchers(HttpMethod.PATCH, "/api/employees/**").hasRole("MANAGER"));

        // use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        // disable CSRF, not required for stateless REST APIs that use POST, PUT, DELETE, and/or PATCH
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
