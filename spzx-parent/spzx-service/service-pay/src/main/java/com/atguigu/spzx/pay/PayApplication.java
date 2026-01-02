package com.atguigu.spzx.pay;

import com.atguigu.spzx.common.annotation.EnableUserLoginAuthInterceptor;
import com.atguigu.spzx.pay.properties.AlipayProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 作者:hfj
 * 功能:支付启动类
 * 日期: 2026/1/2 16:22
 */
@SpringBootApplication
@EnableUserLoginAuthInterceptor
@EnableFeignClients(basePackages = {"com.atguigu.spzx"})
@EnableConfigurationProperties(value = {AlipayProperties.class})
public class PayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class,args);
    }

}
