package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.BrandService;
import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 作者:hfj
 * 功能:品牌功能
 * 日期: 2025/12/27 8:32
 */
@RestController
@RequestMapping(value="/admin/product/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    //品牌的列表查询
    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<Brand>> findByPage(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit){
        PageInfo<Brand> pageInfo = brandService.findByPage(page,limit);
        return Result.build(pageInfo,ResultCodeEnum.SUCCESS);
    }

    //品牌添加功能
    @PostMapping("/save")
    public Result save(@RequestBody Brand brand){
        brandService.save(brand);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    //品牌修改功能
    @PutMapping("/update")
    public Result update(@RequestBody Brand brand){
        brandService.update(brand);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    //品牌删除
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") Long id) {
        brandService.deleteById(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}
