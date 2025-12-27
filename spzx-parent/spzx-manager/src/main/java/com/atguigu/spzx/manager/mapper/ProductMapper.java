package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    //条件分页查询
    List<Product> findByPage(ProductDto productDto);

    //保存商品基本信息
    void save(Product product);

    //根据id查询商品基本信息
    Product findProductById(Long id);

    //修改商品基本数据
    void updateById(Product product);
}
