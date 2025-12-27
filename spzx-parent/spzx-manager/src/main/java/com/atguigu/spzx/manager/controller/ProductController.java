package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.ProductService;
import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 作者:hfj
 * 功能:商品管理功能
 * 日期: 2025/12/27 16:37
 */
@RestController
@RequestMapping(value="/admin/product/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //条件分页查询
    @GetMapping("/{page}/{limit}")
    public Result findByPage(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit, ProductDto productDto){
        PageInfo<Product> pageInfo = productService.findByPage(page,limit,productDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    //添加商品信息
    @PostMapping("/save")
    public Result save(@RequestBody Product product){
        productService.save(product);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}
