package com.nimacode.rest.webservices.restfulwebservices.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 1- all requests should be athenticated
        http.authorizeRequests(
                auth -> auth.anyRequest().authenticated());

        // 2- if a request is not athenticated, a web page is shown
        // this shows pop up instead of webpage
        http.httpBasic(withDefaults());

        // 3- enabling POST and PUT requests
        http.csrf().disable();

        return http.build();
    }

}
