package com.example.querydsl.jpa.child;

import com.example.querydsl.entity.UserBean;
import com.example.querydsl.jpa.MyBaseRepository;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author lihuaqing
 * @create 2019-02-21 13:35
 **/
public interface UserRepository extends MyBaseRepository<UserBean, Long> {
    UserBean findByaddress(String address);
    Optional<UserBean> findOptionalByaddress(String address);
    //我们把list 改成对应的流对象
    Stream<UserBean> findAll();

    Stream<UserBean> findByname(String name);
    // 使用原生的对象进行查询
    @Query(value = "select * from t_user order by t_id desc",nativeQuery = true)
    List<UserBean> readAllByNameNotNull();

    //根据名字返回用户信息
    @Query(value = "select * from t_user where t_name like ?1",nativeQuery = true)
    UserBean selectonebyname(String name);

    //使用命名参数
    /**
     * 这边注意的是，如果select u     ,     UserBean      ,:
     * 也可以使用Spel表达式 的方法实现
     * */
    @Query("select u from UserBean u where u.name = :name and u.address = :address")
    UserBean findBynameOraddress(@Param("name") String name,
                                   @Param("address") String address);


    @Modifying
    @Query("update UserBean u set u.name = :name where u.id = 1")
    int updateuserByname(@Param("name") String name);
}