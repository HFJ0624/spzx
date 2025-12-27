package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.product.ProductSpec;
import com.github.pagehelper.PageInfo;

public interface ProductSpecService {

    //商品管理列表
    PageInfo<ProductSpec> findByPage(Integer page, Integer limit);

    //商品添加功能
    void save(ProductSpec productSpec);

    //商品修改功能
    void updateById(ProductSpec productSpec);

    //商品删除功能
    void deleteById(Long id);
}
