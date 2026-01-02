package com.atguigu.spzx.order.service;

import com.atguigu.spzx.model.dto.h5.OrderInfoDto;
import com.atguigu.spzx.model.entity.order.OrderInfo;
import com.atguigu.spzx.model.vo.h5.TradeVo;

public interface OrderInfoService {

    //购物车下单
    TradeVo getTrade();

    //生成订单
    Long submitOrder(OrderInfoDto orderInfoDto);

    //获取订单信息
    OrderInfo getOrderInfo(Long orderId);

    //立即购买
    TradeVo buy(Long skuId);
}
