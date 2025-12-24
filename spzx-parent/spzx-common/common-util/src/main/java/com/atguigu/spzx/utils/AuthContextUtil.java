package com.atguigu.spzx.utils;

import com.atguigu.spzx.model.entity.system.SysUser;

/**
 * 作者:hfj
 * 功能:对ThreadLocal进行封装
 * 日期: 2025/12/24 18:32
 */
public class AuthContextUtil {

    //创建一个ThreadLocal对象
    private static final ThreadLocal<SysUser> threadLocal = new ThreadLocal<>() ;

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
}
