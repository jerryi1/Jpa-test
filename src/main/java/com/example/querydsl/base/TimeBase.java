package com.example.querydsl.base;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.sql.Timestamp;

/**
 * 数据库基本时间结构
 * 包含创建时间与更新时间
 * 创建时间不允许更新
 * 更新时间作为乐观锁
 * Created by IntelliJ IDEA.
 * AbstractUser: gaochen
 * Date: 2018/12/5
 */
@Data
@MappedSuperclass
public class TimeBase {

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    protected Timestamp createTime;

    @Version
    @UpdateTimestamp
    @Column(nullable = false)
    protected Timestamp updateTime;
}
