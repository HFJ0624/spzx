package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.product.CategoryBrandDto;
import com.atguigu.spzx.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryBrandMapper {

    //分类品牌条件分页查询
    List<CategoryBrand> findByPage(CategoryBrandDto categoryBrandDto);

    //分类品牌添加功能
    void save(CategoryBrand categoryBrand);

    //分类品牌删除功能
    void deleteById(Long id);

    //分类品牌修改功能
    void updateById(CategoryBrand categoryBrand);
}
