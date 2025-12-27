package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductSpecMapper {

    //商品管理列表
    List<ProductSpec> findByPage();

    //商品添加功能
    void save(ProductSpec productSpec);

    //商品修改功能
    void updateById(ProductSpec productSpec);

    //商品删除功能
    void deleteById(Long id);
}
