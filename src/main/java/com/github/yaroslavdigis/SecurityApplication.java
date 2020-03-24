package com.github.yaroslavdigis;

import com.github.yaroslavdigis.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@SpringBootApplication
public class SecurityApplication {

    private final UserRepository userRepo;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping("/sign-up")
    public User signUp(@RequestBody User user) {
        user.setPassword(passwordEncoder().encode(user.getIid()));
        return userRepo.save(user);
    }

    @GetMapping("/test")
    public String test() {
        return "RAMA!";
    }

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
}
