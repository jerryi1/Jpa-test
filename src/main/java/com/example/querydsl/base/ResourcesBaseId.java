package com.example.querydsl.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 基础资源联合主键
 * Created by hanxiao on 2017/4/10.
 */
@Data
public class ResourcesBaseId implements Serializable {

    private Integer id;

    private String url;
}
