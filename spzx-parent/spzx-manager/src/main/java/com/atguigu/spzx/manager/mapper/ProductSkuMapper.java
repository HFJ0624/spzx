package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductSkuMapper {

    //获取商品sku列表集合,保存sku信息
    void save(ProductSku productSku);

    //根据id查询商品sku信息列表
    List<ProductSku> findProductSkuByProductId(Long id);

    //修改商品的sku数据
    void updateById(ProductSku productSku);
}
