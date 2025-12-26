package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.CategoryService;
import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 作者:hfj
 * 功能:分类管理功能
 * 日期: 2025/12/26 14:37
 */
@RestController
@RequestMapping(value="/admin/product/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //分类列表,每次查询一层数据
    @Operation(summary = "根据parentId获取下级节点")
    @GetMapping(value = "/findCategoryList/{id}")
    public Result<List<Category>> findByParentId(@PathVariable("id") Long id){
        List<Category> list = categoryService.findCategoryList(id);
        return Result.build(list,ResultCodeEnum.SUCCESS);
    }
}
