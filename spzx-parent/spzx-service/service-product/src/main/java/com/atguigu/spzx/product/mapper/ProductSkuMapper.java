package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.dto.h5.ProductSkuDto;
import com.atguigu.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductSkuMapper {

    //根据销量排序,获取前10条记录
    List<ProductSku> findProductSkuBySale();

    //商品的分页查询
    List<ProductSku> findByPage(ProductSkuDto productSkuDto);
}
