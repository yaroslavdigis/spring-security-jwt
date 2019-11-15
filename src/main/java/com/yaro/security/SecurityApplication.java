package com.yaro.security;

import com.yaro.security.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@SpringBootApplication
public class SecurityApplication {

    private final UserRepository appUserRepo;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping("/sign-up")
    public User signUp(@RequestBody User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return appUserRepo.save(user);
    }

    @GetMapping("/test")
    public String test() {
        return "RAMA!";
    }

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
}
