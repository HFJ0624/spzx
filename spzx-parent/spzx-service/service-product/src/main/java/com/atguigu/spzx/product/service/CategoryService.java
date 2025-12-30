package com.atguigu.spzx.product.service;

import com.atguigu.spzx.model.entity.product.Category;

import java.util.List;

public interface CategoryService {

    //获取所有一级分类
    List<Category> findOneCategory();

    //查询所有分类,按树形封装
    List<Category> findCategoryTree();
}
