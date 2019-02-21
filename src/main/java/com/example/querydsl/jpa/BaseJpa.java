package com.example.querydsl.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author lihuaqing
 * @create 2019-02-19 9:58
 **/
@NoRepositoryBean
public interface BaseJpa<T>
        extends JpaRepository<T,Long>,
        JpaSpecificationExecutor<T>,
        QueryDslPredicateExecutor<T>
{
}

