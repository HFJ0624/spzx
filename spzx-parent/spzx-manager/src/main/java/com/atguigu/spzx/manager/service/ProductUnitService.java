package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.base.ProductUnit;

import java.util.List;

public interface ProductUnitService {

    //查询所有计量单位
    List<ProductUnit> findAll();
}
