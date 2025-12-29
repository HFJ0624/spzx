package com.atguigu.spzx.product.service.impl;

import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.product.mapper.CategoryMapper;
import com.atguigu.spzx.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者:hfj
 * 功能:
 * 日期: 2025/12/29 17:27
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    //获取所有一级分类
    @Override
    public List<Category> findOneCategory() {
        List<Category> categoryList = categoryMapper.findOneCategory();
        return categoryList;
    }
}
