package com.atguigu.spzx.product.service;

import com.atguigu.spzx.model.dto.h5.ProductSkuDto;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductService {

    //根据销量排序,获取前10条记录
    List<ProductSku> findProductSkuBySale();

    //商品的分页查询
    PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto);
}
