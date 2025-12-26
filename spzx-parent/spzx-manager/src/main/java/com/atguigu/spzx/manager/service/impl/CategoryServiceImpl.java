package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.CategoryMapper;
import com.atguigu.spzx.manager.service.CategoryService;
import com.atguigu.spzx.model.entity.product.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 作者:hfj
 * 功能:
 * 日期: 2025/12/26 14:37
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    //分类列表,每次查询一层数据
    @Override
    public List<Category> findCategoryList(Long id) {
        //1.根据parentId值进行查询,返回list集合
        List<Category> categoryList = categoryMapper.selectByParentId(id);

        //2.遍历返回list集合,判断每个分类是否有下一层分类,如果有设置hasChildren = true
        if (!CollectionUtils.isEmpty(categoryList)){
            categoryList.forEach(category -> {
                //判断下一层是否还有分类
                int count = categoryMapper.countByParentId(category.getId());

                if (count > 0){ //有下层分类
                    category.setHasChildren(true);
                }else { //没有下层分类
                    category.setHasChildren(false);
                }
            });
        }

        return categoryList;
    }
}
