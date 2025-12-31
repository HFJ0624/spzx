package com.atguigu.spzx.cart;

import com.atguigu.spzx.common.annotation.EnableUserLoginAuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 作者:hfj
 * 功能:购物车启动类
 * 日期: 2025/12/31 10:08
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) // 排除数据库的自动化配置，Cart微服务不需要访问数据库
@EnableFeignClients(basePackages = {"com.atguigu.spzx"})
@EnableUserLoginAuthInterceptor
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class , args) ;
    }

}
