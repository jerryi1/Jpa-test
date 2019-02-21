package com.example.querydsl.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author lihuaqing
 * @create 2019-02-19 11:32
 **/
@Entity
@Table(name = "good_types")
@Data
public class GoodTypeBean
        implements Serializable
{
    //主键
    @Id
    @GeneratedValue
    @Column(name = "tgt_id")
    private Long id;
    //类型名称
    @Column(name = "tgt_name")
    private String name;
    //是否显示
    @Column(name = "tgt_is_show")
    private int isShow;
    //排序
    @Column(name = "tgt_order")
    private int order;
}