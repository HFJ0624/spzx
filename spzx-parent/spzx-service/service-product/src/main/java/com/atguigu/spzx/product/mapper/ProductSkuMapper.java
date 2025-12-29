package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductSkuMapper {

    //根据销量排序,获取前10条记录
    List<ProductSku> findProductSkuBySale();
}
