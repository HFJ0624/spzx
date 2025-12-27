package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.ProductSpecService;
import com.atguigu.spzx.model.entity.product.ProductSpec;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 作者:hfj
 * 功能:商品规格管理
 * 日期: 2025/12/27 16:17
 */
@RestController
@RequestMapping(value="/admin/product/productSpec")
public class ProductSpecController {

    @Autowired
    private ProductSpecService productSpecService;

    //商品管理列表
    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<ProductSpec>> findByPage(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit) {
        PageInfo<ProductSpec> pageInfo = productSpecService.findByPage(page,limit);
        return Result.build(pageInfo,ResultCodeEnum.SUCCESS);
    }

    //商品添加功能
    @PostMapping("/save")
    public Result save(@RequestBody ProductSpec productSpec){
        productSpecService.save(productSpec);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    //商品修改功能
    @PutMapping("/updateById")
    public Result updateById(@RequestBody ProductSpec productSpec){
        productSpecService.updateById(productSpec);
        return Result.build(null , ResultCodeEnum.SUCCESS);
    }

    //商品删除功能
    @DeleteMapping("/deleteById/{id}")
    public Result removeById(@PathVariable("id") Long id){
        productSpecService.deleteById(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

}
