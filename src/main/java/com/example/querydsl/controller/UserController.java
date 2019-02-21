package com.example.querydsl.controller;

import com.example.querydsl.entity.QUserBean;
import com.example.querydsl.entity.UserBean;
import com.example.querydsl.jpa.UserJpa;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

/**
 * @author lihuaqing
 * @create 2019-02-19 10:13
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController
{
    @Autowired
    private UserJpa userJpa;
    //实体管理者
//    @Autowired
//    private EntityManager entityManager;

    //JPA查询工厂
    @Autowired
    private JPAQueryFactory queryFactory;

//    @PostConstruct
//    public void initFactory()
//    {
//        queryFactory = new JPAQueryFactory(entityManager);
//        System.out.println("init JPAQueryFactory successfully");
//    }

    /**
     * 查询全部数据并根据id倒序
     * @return
     */
    @RequestMapping(value = "/queryAll")
    public List<UserBean> queryAll()
    {
        //使用querydsl查询
        QUserBean _Q_user = QUserBean.userBean;
        //查询并返回结果集
        return queryFactory
                .selectFrom(_Q_user)//查询源
                .orderBy(_Q_user.id.desc())//根据id倒序
                .fetch();//执行查询并获取结果集
    }

    @RequestMapping(value = "/detail/{name}")
    public UserBean detail(@PathVariable("name") String name)
    {
        //使用querydsl查询
        QUserBean _Q_user = QUserBean.userBean;
        //查询并返回结果集
        return userJpa.findOne(_Q_user.name.like("%"+name+"%"));
    }

    @RequestMapping(value = "/saveuser")
    public void save(@RequestBody  UserBean userBean)
    {
        //更新数据的操作
        userJpa.save(userBean);
    }
    @RequestMapping(value = "/updateUser")
    @Transactional
    public void update(@RequestBody  UserBean userBean)
    {
        QUserBean _Q_user = QUserBean.userBean;
        //更新数据的操作
        queryFactory.update(_Q_user).set(_Q_user.name,userBean.getName()).where(_Q_user.id.eq(userBean.getId())).execute();
    }
}