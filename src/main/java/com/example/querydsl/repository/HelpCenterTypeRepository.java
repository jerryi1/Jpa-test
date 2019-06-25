package com.example.querydsl.repository;

import com.example.querydsl.entity.HelpCenterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;
import java.util.Set;

public interface HelpCenterTypeRepository extends JpaRepository<HelpCenterType,Long>, QueryDslPredicateExecutor<HelpCenterType> {
    //根据传入ids删除
    void deleteByIdIn(List<Long> ids);
    //删除子节点信息
    void deleteAllByParentIdIn(List<Long> ids);
    //根据父节点查询子列表
    List<HelpCenterType> findAllByParentId(Long parentId);
    //查询出所有的结果并且显示为true的
    Set<HelpCenterType> findAllByIsShowIsTrue();
}
