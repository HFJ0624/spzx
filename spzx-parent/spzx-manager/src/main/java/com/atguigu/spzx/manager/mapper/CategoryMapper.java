package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    //根据parentId值进行查询,返回list集合
    List<Category> selectByParentId(Long id);

    //判断下一层是否还有分类
    int countByParentId(Long id);
}
