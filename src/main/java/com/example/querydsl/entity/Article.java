package com.example.querydsl.entity;

import com.example.querydsl.base.TimeBase;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * 文章的实体对象
 * */
@Data
@Entity
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Article extends TimeBase {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    //文章标题
    private String title;

    //缩略图
    private String picture;

    //排序
    private Integer sortNum;

    //文章简介
    private  String brief;

    //文章内容
    private String content;

//    //附件上传
//    private List<String> attachs;

    //SEO标题
    private String seoTitle;

    //SEO关键词
    private String seoKeywords;

    //SEO描述
    private String seoDesc;

    //维持关联关系的字段
    @ManyToOne
    @JoinColumn(name = "type_id")
    private HelpCenterType typeId;
}
