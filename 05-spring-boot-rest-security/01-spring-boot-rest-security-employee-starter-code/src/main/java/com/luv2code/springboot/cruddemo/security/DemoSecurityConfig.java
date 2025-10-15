package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    // add support for JDBC . . . no more hardcoded users
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    // restrict access to certain HTTP methods depending on their roles
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

    // build usernames and passwords for users and assign them roles to access HTTP requests
    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john = User.builder().username("john").password("{noop}test123").roles("EMPLOYEE").build();
        UserDetails mary = User.builder().username("mary").password("{noop}testmary123").roles("EMPLOYEE", "MANAGER").build();
        UserDetails susan = User.builder().username("susan").password("{noop}testsusan123").roles("EMPLOYEE", "MANAGER", "ADMIN").build();

        return new InMemoryUserDetailsManager(john, susan, mary);
    }
    */
}
