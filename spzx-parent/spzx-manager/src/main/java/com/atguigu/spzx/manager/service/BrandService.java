package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.product.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BrandService {

    //品牌的列表查询
    PageInfo<Brand> findByPage(Integer page, Integer limit);

    //品牌添加功能
    void save(Brand brand);

    //品牌修改功能
    void update(Brand brand);

    //品牌删除
    void deleteById(Long id);

    //查询所有品牌
    List<Brand> findAll();
}
