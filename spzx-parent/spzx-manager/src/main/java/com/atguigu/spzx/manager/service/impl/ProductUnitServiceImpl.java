package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.ProductUnitMapper;
import com.atguigu.spzx.manager.service.ProductUnitService;
import com.atguigu.spzx.model.entity.base.ProductUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者:hfj
 * 功能:
 * 日期: 2025/12/27 17:08
 */
@Service
public class ProductUnitServiceImpl implements ProductUnitService {

    @Autowired
    private ProductUnitMapper productUnitMapper;

    //查询所有计量单位
    @Override
    public List<ProductUnit> findAll() {
        List<ProductUnit> productUnitList = productUnitMapper.findAll();
        return productUnitList;
    }
}
