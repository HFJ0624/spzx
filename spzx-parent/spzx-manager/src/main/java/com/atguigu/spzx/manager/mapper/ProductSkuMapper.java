package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductSkuMapper {

    //获取商品sku列表集合,保存sku信息
    void save(ProductSku productSku);
}
