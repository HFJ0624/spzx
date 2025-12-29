package com.atguigu.spzx.product.service;

import com.atguigu.spzx.model.entity.product.ProductSku;

import java.util.List;

public interface ProductService {

    //根据销量排序,获取前10条记录
    List<ProductSku> findProductSkuBySale();
}
