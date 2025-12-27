package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper {

    //品牌的列表查询
    List<Brand> findByPage();

    //品牌添加功能
    void save(Brand brand);

    //品牌修改功能
    void update(Brand brand);

    //品牌删除
    void deleteById(Long id);
}
