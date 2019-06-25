package com.example.querydsl.service;

import com.example.querydsl.base.ITreeValue;
import com.example.querydsl.entity.HelpCenterType;
import com.example.querydsl.pojo.HelpCenterTypeForm;
import com.example.querydsl.repository.HelpCenterTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class HelpCenterService {
    @Autowired
    private HelpCenterTypeRepository helpCenterTypeRepository;

    /**
     * 新增配置分类
     */
    public HelpCenterType add(HelpCenterTypeForm helpCenterTypeForm) {
        //判断父分类是否存在
        HelpCenterType hasHelpCenterType = helpCenterTypeRepository.findOne(helpCenterTypeForm.getParentId());
        if (hasHelpCenterType == null) {
            throw new RuntimeException("新增的数据必须在父标签下");
        }
        HelpCenterType helpCenterType = new HelpCenterType();
        BeanUtils.copyProperties(helpCenterTypeForm, helpCenterType);
        return helpCenterTypeRepository.save(helpCenterType);
    }

    /**
     * 编辑我们的配置分类
     */
    public HelpCenterType update(Long id, HelpCenterTypeForm helpCenterTypeForm) {
        HelpCenterType helpCenterType = helpCenterTypeRepository.findOne(id);
        //判断更新的传入的id 是不是正确
        if (id == 1 || helpCenterType == null) {
            throw new RuntimeException("数据库里面不存在我们对应的数据（id == 1的不可以进行编辑）");
        }
        //判断parentId是否存在
        HelpCenterType hasHelpCenterType = helpCenterTypeRepository.findOne(helpCenterTypeForm.getParentId());
        if (hasHelpCenterType == null) {
            throw new RuntimeException("编辑的数据必须在父标签下");
        }
        //编辑的时候忽略父分类的编辑
        BeanUtils.copyProperties(helpCenterTypeForm, helpCenterType,"parentId");
        return helpCenterTypeRepository.save(helpCenterType);
    }

    /**
     * 删除我们的配置分类
     */
    @Transactional
    public void delete(List<Long> ids) {
        //id=1的结点不可以进行删除
        if (ids.contains(1L)) {
            throw new RuntimeException("id = 1 的这个结点不可以进行删除");
        }
        //删除基础结点
        helpCenterTypeRepository.deleteByIdIn(ids);
        //如果对应的子节点
        helpCenterTypeRepository.deleteAllByParentIdIn(ids);
    }


    /**
     * 根据父节点子分类列表
     */
    public List<HelpCenterType> getChildList(Long parentId) {
        //这里面我们只找寻的是一级，不能找寻所有级别
        return helpCenterTypeRepository.findAllByParentId(parentId);
    }

    public ITreeValue getTree() {
        Set<HelpCenterType> sets = helpCenterTypeRepository.findAllByIsShowIsTrue();
        Optional<ITreeValue> treeValue =  ITreeValue.merge(sets);
        return treeValue.get();
    }
}
