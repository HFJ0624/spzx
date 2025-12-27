package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.product.CategoryBrandDto;
import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.model.entity.product.CategoryBrand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CategoryBrandService {

    //分类品牌条件分页查询
    PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto);

    //分类品牌添加功能
    void save(CategoryBrand categoryBrand);

    //分类品牌删除功能
    void deleteById(Long id);

    //分类品牌修改功能
    void updateById(CategoryBrand categoryBrand);

    //根据分类id查询对应的品牌数据
    List<Brand> findBrandByCategoryId(Long categoryId);
}
