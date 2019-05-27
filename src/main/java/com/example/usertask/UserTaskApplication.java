package com.example.usertask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UserTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserTaskApplication.class, args);
    }

}
