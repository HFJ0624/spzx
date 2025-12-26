package com.atguigu.spzx.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.manager.mapper.CategoryMapper;
import com.atguigu.spzx.manager.service.CategoryService;
import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
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

    //导出文件功能
    @Override
    public void exportData(HttpServletResponse response) {
        try {
            //1.设置响应头信息和其他信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");

            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("分类数据", "UTF-8");
            //响应头的设置
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            //2.调用mapper查询所有分类,返回list集合
            List<Category> categoryList = categoryMapper.selectAll();

            //将从数据库中查询到的Category对象转换成CategoryExcelVo对象
            List<CategoryExcelVo> categoryExcelVoList = new ArrayList<>(categoryList.size());
            for(Category category : categoryList) {
                CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
                //把category的值获取出来 设置到categoryExcelVo中(用BeanUtils工具类转换)
                BeanUtils.copyProperties(category, categoryExcelVo, CategoryExcelVo.class);
                categoryExcelVoList.add(categoryExcelVo);
            }

            //3.调用EasyExcel的写方法导出数据
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class).sheet("分类数据").doWrite(categoryExcelVoList);

        } catch (IOException e) {
            e.printStackTrace();
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
    }
}
