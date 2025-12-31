package com.atguigu.spzx.user;

import com.atguigu.spzx.common.annotation.EnableUserLoginAuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 作者:hfj
 * 功能:用户的实体启动类
 * 日期: 2025/12/30 19:46
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.atguigu.spzx"})
@EnableUserLoginAuthInterceptor
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
