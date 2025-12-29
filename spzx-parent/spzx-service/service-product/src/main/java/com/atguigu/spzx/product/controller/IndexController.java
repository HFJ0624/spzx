package com.atguigu.spzx.product.controller;

import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.h5.IndexVo;
import com.atguigu.spzx.product.service.CategoryService;
import com.atguigu.spzx.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 作者:hfj
 * 功能:首页接口管理
 * 日期: 2025/12/29 17:24
 */
@Tag(name = "首页接口管理")
@RestController
@RequestMapping(value="/api/product/index")
@SuppressWarnings({"unchecked", "rawtypes"})
@CrossOrigin
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Operation(summary = "获取首页数据")
    @GetMapping
    public Result<IndexVo> index(){
        //1.获取所有一级分类
        List<Category> categoryList = categoryService.findOneCategory();

        //2.根据销量排序,获取前10条记录
        List<ProductSku> productSkuList = productService.findProductSkuBySale();

        //3.返回封装数据
        IndexVo indexVo = new IndexVo();
        indexVo.setCategoryList(categoryList);
        indexVo.setProductSkuList(productSkuList);
        return Result.build(indexVo,ResultCodeEnum.SUCCESS);
    }
}
