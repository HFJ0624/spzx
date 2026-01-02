package com.atguigu.spzx.order.controller;

import com.atguigu.spzx.model.dto.h5.OrderInfoDto;
import com.atguigu.spzx.model.entity.order.OrderInfo;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.h5.TradeVo;
import com.atguigu.spzx.order.service.OrderInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 作者:hfj
 * 功能:订单管理
 * 日期: 2026/1/2 9:52
 */
@Tag(name = "订单管理")
@RestController
@RequestMapping(value="/api/order/orderInfo")
@SuppressWarnings({"unchecked", "rawtypes"})
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    //购物车下单
    @Operation(summary = "确认下单")
    @GetMapping("/auth/trade")
    public Result<TradeVo> trade() {
        TradeVo tradeVo = orderInfoService.getTrade();
        return Result.build(tradeVo,ResultCodeEnum.SUCCESS);
    }

    //生成订单
    @Operation(summary = "提交订单")
    @PostMapping("/auth/submitOrder")
    public Result<Long> submitOrder(@Parameter(name = "orderInfoDto", description = "请求参数实体类", required = true) @RequestBody OrderInfoDto orderInfoDto) {
        Long orderId = orderInfoService.submitOrder(orderInfoDto);
        return Result.build(orderId,ResultCodeEnum.SUCCESS);
    }

    //获取订单信息
    @Operation(summary = "获取订单信息")
    @GetMapping("/auth/{orderId}")
    public Result<OrderInfo> getOrderInfo(@Parameter(name = "orderId", description = "订单id", required = true) @PathVariable("orderId") Long orderId) {
        OrderInfo orderInfo = orderInfoService.getOrderInfo(orderId);
        return Result.build(orderInfo,ResultCodeEnum.SUCCESS);
    }
}
