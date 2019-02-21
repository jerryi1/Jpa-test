package com.example.querydsl;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lihuaqing
 * @create 2019-02-19 12:49
 **/
@Data
public class GoodDto implements Serializable {
    //主键
    private Long id;
    //标题
    private String title;
    //单位
    private String unit;
    //价格
    private double price;
    //类型名称
    private String typeName;
    //类型编号
    private Long typeId;
}