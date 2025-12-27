package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.ProductUnitService;
import com.atguigu.spzx.model.entity.base.ProductUnit;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 作者:hfj
 * 功能:商品单元数据
 * 日期: 2025/12/27 17:06
 */
@RestController
@RequestMapping("/admin/product/productUnit")
public class ProductUnitController {

    @Autowired
    private ProductUnitService productUnitService;

    //查询所有计量单位
    @GetMapping("/findAll")
    public Result<List<ProductUnit>> findAll() {
        List<ProductUnit> productUnitList = productUnitService.findAll();
        return Result.build(productUnitList,ResultCodeEnum.SUCCESS);
    }
}
