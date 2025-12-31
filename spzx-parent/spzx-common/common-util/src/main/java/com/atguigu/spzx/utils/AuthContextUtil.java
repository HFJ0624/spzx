package com.atguigu.spzx.utils;

import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.entity.user.UserInfo;

/**
 * 作者:hfj
 * 功能:对ThreadLocal进行封装
 * 日期: 2025/12/24 18:32
 */
public class AuthContextUtil {

    //创建一个ThreadLocal对象
    private static final ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();

    private static final ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>();

    //添加数据
    public static void set(SysUser sysUser) {
        threadLocal.set(sysUser);
    }

    //获取数据的方法
    public static SysUser get() {
        return threadLocal.get() ;
    }

    //删除数据的方法
    public static void remove() {
        threadLocal.remove();
    }

    // 定义存储数据的静态方法
    public static void setUserInfo(UserInfo userInfo) {
        userInfoThreadLocal.set(userInfo);
    }

    // 定义获取数据的方法
    public static UserInfo getUserInfo() {
        return userInfoThreadLocal.get() ;
    }

    // 删除数据的方法
    public static void removeUserInfo() {
        userInfoThreadLocal.remove();
    }
}
