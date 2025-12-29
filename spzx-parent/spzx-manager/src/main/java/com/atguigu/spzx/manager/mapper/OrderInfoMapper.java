package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderInfoMapper {

    //根据前一天日期进行统计(按天分组)
    OrderStatistics selectOrderStatistics(String createTime);
}
