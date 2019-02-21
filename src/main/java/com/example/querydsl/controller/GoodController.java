package com.example.querydsl.controller;

import com.example.querydsl.GoodDto;
import com.example.querydsl.entity.GoodInfoBean;
import com.example.querydsl.entity.QGoodInfoBean;
import com.example.querydsl.entity.QGoodTypeBean;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lihuaqing
 * @create 2019-02-19 11:38
 **/
@RestController
@RequestMapping("/goods")
public class GoodController {
    //查询工厂实体
    @Autowired
    private JPAQueryFactory queryFactory;

    @RequestMapping(value = "/selectByType")
    public List<GoodInfoBean> selectByType(@RequestParam(value = "typeId") Long typeId)
    {
        //商品查询实体
        QGoodInfoBean _Q_good = QGoodInfoBean.goodInfoBean;
        //商品类型查询实体
        QGoodTypeBean _Q_good_type = QGoodTypeBean.goodTypeBean;
        return queryFactory.select(_Q_good)
                        .from(_Q_good,_Q_good_type)
                        .where(
                                //为两个实体关联查询
                                _Q_good.typeId.eq(_Q_good_type.id)
                                        .and(
                                                //查询指定typeid的商品
                                                _Q_good_type.id.eq(typeId)
                                        )
                        )
                        //根据排序字段倒序
                        .orderBy(_Q_good.order.desc())
                        //执行查询
                        .fetch();
    }


    @RequestMapping(value = "/selectbydefined")
    public List<GoodDto> selectbydefined(@RequestParam(value = "typeId") Long typeId)
    {
        //商品查询实体
        QGoodInfoBean _Q_good = QGoodInfoBean.goodInfoBean;
        //商品类型查询实体
        QGoodTypeBean _Q_good_type = QGoodTypeBean.goodTypeBean;
        return queryFactory.select(Projections.bean(
          GoodDto.class,
          _Q_good.id,
          _Q_good.price,
          _Q_good.title,
          _Q_good.unit,
          //使用别名对应dto内的typeName
          _Q_good_type.name.as("typeName"),
          _Q_good_type.id.as("typeId")
        ))
                .from(_Q_good,_Q_good_type)
                .where(
                        //为两个实体关联查询
                        _Q_good.typeId.eq(_Q_good_type.id)
                                .and(
                                        //查询指定typeid的商品
                                        _Q_good_type.id.eq(typeId)
                                )
                )
                //根据排序字段倒序
                .orderBy(_Q_good.order.desc())
                //执行查询
                .fetch();
    }
}