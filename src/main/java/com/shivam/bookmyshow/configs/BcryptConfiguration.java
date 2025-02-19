package com.shivam.bookmyshow.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BcryptConfiguration {
    @Bean
    BCryptPasswordEncoder getBcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
