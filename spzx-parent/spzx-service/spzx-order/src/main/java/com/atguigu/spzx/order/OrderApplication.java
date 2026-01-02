package com.atguigu.spzx.order;

import com.atguigu.spzx.common.annotation.EnableUserTokenFeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 作者:hfj
 * 功能:订单启动类
 * 日期: 2026/1/2 9:51
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.atguigu.spzx"})
@EnableUserTokenFeignInterceptor
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class , args) ;
    }

}
