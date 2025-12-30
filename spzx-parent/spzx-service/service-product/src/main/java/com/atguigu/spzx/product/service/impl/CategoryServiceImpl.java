package com.atguigu.spzx.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.product.mapper.CategoryMapper;
import com.atguigu.spzx.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 作者:hfj
 * 功能:
 * 日期: 2025/12/29 17:27
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //获取所有一级分类
    @Override
    public List<Category> findOneCategory() {
        //1.查询redis,是否有所有一级分类
        String categoryListJSON = redisTemplate.opsForValue().get("category:one");

        //2.如果redis包含所有一级分类,直接返回
        if(StringUtils.hasText(categoryListJSON)) {
            List<Category> existCategoryList = JSON.parseArray(categoryListJSON, Category.class);
            return existCategoryList;
        }

        //3.如果redis没有所有一级分类,查询数据库,把数据库查询内容返回,并且查询内容放到redis里面
        List<Category> categoryList = categoryMapper.findOneCategory();
        redisTemplate.opsForValue().set("category:one" , JSON.toJSONString(categoryList) , 7 , TimeUnit.DAYS);
        return categoryList;
    }

    //查询所有分类,按树形封装
    @Cacheable(value = "category",key = "'all'")
    @Override
    public List<Category> findCategoryTree() {
        //1.查询所有分类,返回list集合
        List<Category> categoryList = categoryMapper.findAll();

        //2.遍历所有分类list集合,得到全部一级分类
        List<Category> oneCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == 0).collect(Collectors.toList());

        if(!CollectionUtils.isEmpty(oneCategoryList)) {
            //3.遍历第二层,得到二级分类
            oneCategoryList.forEach(oneCategory -> {
                List<Category> twoCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == oneCategory.getId().longValue()).collect(Collectors.toList());
                //把二级分类封装到一级分类
                oneCategory.setChildren(twoCategoryList);

                if(!CollectionUtils.isEmpty(twoCategoryList)) {
                    //3.遍历第三层,得到三级分类
                    twoCategoryList.forEach(twoCategory -> {
                        List<Category> threeCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == twoCategory.getId().longValue()).collect(Collectors.toList());
                        //把三级分类封装到二级分类
                        twoCategory.setChildren(threeCategoryList);
                    });
                }
            });
        }
        return oneCategoryList;
    }
}
