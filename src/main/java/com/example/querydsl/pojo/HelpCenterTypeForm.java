package com.example.querydsl.pojo;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class HelpCenterTypeForm {
    //文章标题
    private String name;
    //父类
    private Long parentId;
    //排序
    private Integer sortNum;
    //是否显示
    private Boolean isShow;
    //批量删除的ids
    private List<Long> ids;
}
