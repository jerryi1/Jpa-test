package com.example.querydsl.jpa;
import com.example.querydsl.entity.UserBean;
import org.springframework.data.repository.CrudRepository;

public interface MyUserCrudRepository extends CrudRepository<UserBean,Long> {
    long countByname(String name);
}
