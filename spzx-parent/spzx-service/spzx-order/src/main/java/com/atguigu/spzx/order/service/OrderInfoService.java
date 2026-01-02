package com.atguigu.spzx.order.service;

import com.atguigu.spzx.model.dto.h5.OrderInfoDto;
import com.atguigu.spzx.model.vo.h5.TradeVo;

public interface OrderInfoService {

    //购物车下单
    TradeVo getTrade();

    //生成订单
    Long submitOrder(OrderInfoDto orderInfoDto);
}
