package com.atguigu.spzx.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 作者:hfj
 * 功能:
 * 日期: 2025/12/24 20:40
 */
@Data
@ConfigurationProperties(prefix = "spzx.auth")      // 前缀不能使用驼峰命名
public class UserProperties {

    //不拦截的接口路径
    private List<String> noAuthUrls;
}
