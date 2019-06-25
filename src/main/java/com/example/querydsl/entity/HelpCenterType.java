package com.example.querydsl.entity;

import com.example.querydsl.base.ITreeValue;
import com.example.querydsl.base.TimeBase;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class HelpCenterType extends TimeBase implements ITreeValue {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    //文章标题
    private String name;
    //父类
    private Integer parentId;
    //排序
    private Integer sortNum;
    //是否显示
    @ColumnDefault("true")
    private Boolean isShow;

    @JsonProperty("son")
    @Transient
    private List<HelpCenterType> sonList;

    //获取子列表
    @Override
    public List<? extends ITreeValue> getSonList() {
        return sonList;
    }

    //设置子列表
    @Override
    public void setSonList(Set<ITreeValue> iTreeValues) {
        this.sonList = iTreeValues == null ? null :
                iTreeValues.stream().map(tree -> (HelpCenterType) tree).collect(Collectors.toList());
    }

    //和文章之间有一对多的关系
    @OneToMany(mappedBy = "typeId",cascade = CascadeType.REMOVE)
    private List<Article> articles;
}
