package com.example.querydsl.repository;

import com.example.querydsl.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface ArticleRepository extends JpaRepository<Article,Long>, QueryDslPredicateExecutor<Article> {
}
