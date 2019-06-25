package com.example.querydsl.controller;

import com.example.querydsl.base.BaseController;
import com.example.querydsl.entity.Article;
import com.example.querydsl.pojo.ArticleForm;
import com.example.querydsl.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController {
    @Autowired
    private ArticleService articleService;

    //新增文章
    public ResponseEntity<?> save(@RequestBody @Validated ArticleForm articleForm) {
        articleService.save(articleForm);
        return null;
    }


    //删除文章


    //修改文章


    //编辑文章
}
