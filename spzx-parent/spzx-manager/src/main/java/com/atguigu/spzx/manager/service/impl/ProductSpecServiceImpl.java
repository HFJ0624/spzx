package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.ProductSpecMapper;
import com.atguigu.spzx.manager.service.ProductSpecService;
import com.atguigu.spzx.model.entity.product.ProductSpec;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者:hfj
 * 功能:
 * 日期: 2025/12/27 16:17
 */
@Service
public class ProductSpecServiceImpl implements ProductSpecService {

    @Autowired
    private ProductSpecMapper productSpecMapper;

    //商品管理列表
    @Override
    public PageInfo<ProductSpec> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<ProductSpec> productSpecList = productSpecMapper.findByPage();
        PageInfo<ProductSpec> pageInfo = new PageInfo<>(productSpecList);
        return pageInfo;
    }

    //商品添加功能
    @Override
    public void save(ProductSpec productSpec) {
        productSpecMapper.save(productSpec);
    }

    //商品修改功能
    @Override
    public void updateById(ProductSpec productSpec) {
        productSpecMapper.updateById(productSpec);
    }

    //商品删除功能
    @Override
    public void deleteById(Long id) {
        productSpecMapper.deleteById(id);
    }

    //查询商品所有规格
    @Override
    public List<ProductSpec> findAll() {
        List<ProductSpec> productSpecList = productSpecMapper.findAll();
        return productSpecList;
    }
}
