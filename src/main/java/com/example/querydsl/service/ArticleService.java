package com.example.querydsl.service;

import com.example.querydsl.entity.Article;
import com.example.querydsl.entity.HelpCenterType;
import com.example.querydsl.pojo.ArticleForm;
import com.example.querydsl.repository.ArticleRepository;
import com.example.querydsl.repository.HelpCenterTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private HelpCenterTypeRepository helpCenterTypeRepository;
    public Article save(ArticleForm articleForm) {
        //基础数据的赋值
        Article article = new Article();
        BeanUtils.copyProperties(articleForm,article);
        //设置分类
        Long typeId = articleForm.getTypeId();
        HelpCenterType type = helpCenterTypeRepository.findOne(typeId);
        article.setTypeId(type);
        ///设置附件
        return null;
    }
}
