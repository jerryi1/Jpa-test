package com.example.querydsl.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class ArticleForm {
    //文章标题
    @NotNull
    private String title;

    //文章分类
    @NotNull
    private Long typeId;

    //缩略图
    private String picture;

    //排序
    @NotNull
    private Integer sortNum;

    //文章简介
    private  String brief;

    //文章内容
    @NotNull
    private String content;

    //附件上传(附件我们先不管了)
//    private List<String> attachs;

    //SEO标题
    private String seoTitle;

    //SEO关键词
    private String seoKeywords;

    //SEO描述
    private String seoDesc;


}
