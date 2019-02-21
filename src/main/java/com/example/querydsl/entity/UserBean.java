package com.example.querydsl.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author lihuaqing
 * @create 2019-02-19 9:46
 **/
@Data
@Entity
@Table(name = "t_user")
public class UserBean implements Serializable
{
    @Id
    @Column(name = "t_id")
    @GeneratedValue
    private Long id;
    @Column(name = "t_name")
    private String name;
    @Column(name = "t_age")
    private int age;
    @Column(name = "t_address")
    private String address;
    @Column(name = "t_pwd")
    private String pwd;
}