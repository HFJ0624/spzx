package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.product.Category;

import java.util.List;

public interface CategoryService {

    //分类列表,每次查询一层数据
    List<Category> findCategoryList(Long id);
}
