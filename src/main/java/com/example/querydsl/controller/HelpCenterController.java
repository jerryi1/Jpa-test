package com.example.querydsl.controller;

import com.example.querydsl.base.BaseController;
import com.example.querydsl.pojo.HelpCenterTypeForm;
import com.example.querydsl.service.HelpCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 帮助中心管理配置
 * */
@RestController
@RequestMapping("/helpcenter")
public class HelpCenterController extends BaseController {
     @Autowired
     private HelpCenterService helpCenterService;
    /**
     * 新增我们的分类
     * */
    @PostMapping
    public ResponseEntity add(@RequestBody @Validated HelpCenterTypeForm helpCenterTypeForm){
       return created(helpCenterService.add(helpCenterTypeForm));
    }


    /**
     * 编辑我们的分类
     * */
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id,@RequestBody @Validated HelpCenterTypeForm helpCenterTypeForm){
        return created(helpCenterService.update(id,helpCenterTypeForm));
    }

    /**
     * 删除我们的分类
     * */
    @DeleteMapping
    public ResponseEntity delete(@RequestBody HelpCenterTypeForm helpCenterTypeForm){
        helpCenterService.delete(helpCenterTypeForm.getIds());
        return deleted();
    }


    /**
     *查看分类列表（传入我们的父id）
     * */
    @GetMapping("/child")
    public ResponseEntity get(@RequestParam("parentId") Long parentId){
        return ok(helpCenterService.getChildList(parentId));
    }

    /**
     * 加载分类树结构
     * */
    @GetMapping("/tree")
    public ResponseEntity getTree(){
        return ok(helpCenterService.getTree());
    }
}
