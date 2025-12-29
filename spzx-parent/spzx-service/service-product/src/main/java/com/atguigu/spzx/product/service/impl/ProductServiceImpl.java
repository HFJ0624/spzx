package com.atguigu.spzx.product.service.impl;

import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.product.mapper.ProductSkuMapper;
import com.atguigu.spzx.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者:hfj
 * 功能:
 * 日期: 2025/12/29 17:26
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductSkuMapper productSkuMapper;

    //根据销量排序,获取前10条记录
    @Override
    public List<ProductSku> findProductSkuBySale() {
        List<ProductSku> productSkuList = productSkuMapper.findProductSkuBySale();
        return productSkuList;
    }
}
