package com.example.querydsl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class QuerydslApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuerydslApplication.class, args);
    }
}
