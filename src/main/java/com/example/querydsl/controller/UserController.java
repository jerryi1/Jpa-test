package com.example.querydsl.controller;

import com.example.querydsl.entity.QUserBean;
import com.example.querydsl.entity.UserBean;
import com.example.querydsl.jpa.MyUserCrudRepository;
import com.example.querydsl.jpa.UserJpa;
import com.example.querydsl.jpa.child.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Stream;

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

    @Autowired
    private MyUserCrudRepository userCrudRepository;

    @Autowired
    private UserRepository userRepository;


//    @Autowired
//    private UserPageAndSortRepository userPageAndSortRepository;
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
                .selectFrom(_Q_user)
                //查询源
                .orderBy(_Q_user.id.desc())
                //根据id倒序
                .fetch();
                //执行查询并获取结果集
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
    @Transactional(rollbackFor = Exception.class)
    public void update(@RequestBody  UserBean userBean)
    {
        QUserBean _Q_user = QUserBean.userBean;
        //更新数据的操作
        queryFactory.update(_Q_user).set(_Q_user.name,userBean.getName()).where(_Q_user.id.eq(userBean.getId())).execute();
    }

    /**
     * 添加分页和排序
     * */
    @RequestMapping(value = "/findallBypage")
    public void findallBypage()
    {
        //根据id进行倒序排序
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        // 这里面的page需要-1
        PageRequest pageRequest = new PageRequest(0,2,sort);
        Page<UserBean> users  = userJpa.findAll(pageRequest);
        System.out.println(users.getContent());
    }

    /**
     * 验证单独注入
     * */
    @RequestMapping(value = "/querybyMyself")
    public void querybyMyself(){
        UserBean userBean = userCrudRepository.findOne(Long.parseLong("1"));
        Long count = userCrudRepository.countByname("aa");
        System.out.println(count);
        System.out.println(userBean);
    }

    /**
     * 测试自己隐藏一些方法
     * 这边会自己映射SimpleJpaRepository是底层的
     * */
    @RequestMapping(value = "/hiddensomeway")
    public void hiddensomeway(){
       System.out.println(userRepository.findById(Long.parseLong("1")));
       //返回为空的时候(里面存在两个返回的结果)
        Optional<UserBean> users = userRepository.findOptionalByaddress("asdasd");
        System.out.println(users);
        Stream<UserBean> streams = userRepository.findAll();
        streams.forEach(e->{
            System.out.println(e);
        });
    }
//
    @RequestMapping(value = "/streamtest")
    public void streamtest(){
        List<UserBean> stream = userRepository.readAllByNameNotNull();
        System.out.println("返回的数据"+stream);
    }

    @RequestMapping(value = "/querybyname")
    public void querybyname(){
        UserBean userBean = userRepository.selectonebyname("limin");
        System.out.println("返回的信息是："+userBean);
    }

    /**
     * 使用命名参数
     * */
    @RequestMapping(value = "/usernameparam")
    public void usernameparam(){
        UserBean userBean = userRepository.findBynameOraddress("limin","asdasd");
        System.out.println("命名参数返回的信息是："+userBean);
    }

    /**
     * 更新用户名字根据id
     * */
    @Transactional
    @RequestMapping(value = "/updatenameByid")
    public void updatenameByid(){
        int nums = userRepository.updateuserByname("lhq");
        System.out.println("修改数据的个数"+nums);
    }
}