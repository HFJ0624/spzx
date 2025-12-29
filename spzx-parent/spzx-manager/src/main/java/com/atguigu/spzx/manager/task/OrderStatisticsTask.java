package com.atguigu.spzx.manager.task;

import cn.hutool.core.date.DateUtil;
import com.atguigu.spzx.manager.mapper.OrderInfoMapper;
import com.atguigu.spzx.manager.mapper.OrderStatisticsMapper;
import com.atguigu.spzx.model.entity.order.OrderStatistics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者:hfj
 * 功能:定时任务
 * 日期: 2025/12/29 9:37
 */
@Component
@Slf4j
public class OrderStatisticsTask {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    //每天凌晨2点,查询前一天日期统计数据,把统计之后数据添加统计结果表里面
    @Scheduled(cron = "0 0 2 * * ?")
    public void orderTotalAmountStatistics(){
        //1.获取前一天日期
        String createTime = DateUtil.offsetDay(new Date(), -1).toString(new SimpleDateFormat("yyyy-MM-dd"));

        //2.根据前一天日期进行统计(按天分组)
        OrderStatistics orderStatistics = orderInfoMapper.selectOrderStatistics(createTime);

        //3.把统计之后的数据,保存到统计结果表里面
        if(orderStatistics != null) {
            orderStatisticsMapper.insert(orderStatistics);
        }
    }
}
