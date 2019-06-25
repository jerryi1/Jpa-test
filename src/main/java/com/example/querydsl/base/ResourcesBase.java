package com.example.querydsl.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * 文件资源基础
 * Created by hanxiao on 2017/4/8.
 */
@Data
@MappedSuperclass
@IdClass(ResourcesBaseId.class)
@EqualsAndHashCode(of = {"id", "url"})
public abstract class ResourcesBase {

    @Id
    @NotNull
    private Integer id;

    @Id
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @NotNull
    private String url;

}
