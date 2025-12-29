package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderStatisticsMapper {

    //把统计之后的数据,保存到统计结果表里面
    void insert(OrderStatistics orderStatistics);
}
