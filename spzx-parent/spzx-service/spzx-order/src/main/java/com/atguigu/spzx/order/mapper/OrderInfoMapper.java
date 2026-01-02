package com.atguigu.spzx.order.mapper;

import com.atguigu.spzx.model.entity.order.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderInfoMapper {

    //添加数据到order_info表
    void save(OrderInfo orderInfo);

    //获取订单信息
    OrderInfo getById(Long orderId);

    //查询订单信息
    List<OrderInfo> findUserPage(Long userId, Integer orderStatus);
}
