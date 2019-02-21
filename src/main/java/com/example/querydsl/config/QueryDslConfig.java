package com.example.querydsl.config;

/**
 * @author lihuaqing
 * @create 2019-02-19 11:40
 **/

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class QueryDslConfig {
    @Autowired
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory myjpaQueryFactory(){
       return new JPAQueryFactory(entityManager);
    }
}