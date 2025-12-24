package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper {

    //2.根据用户名查询数据库表 sys_user表
    SysUser selectUserInfoByUserName(String userName);
}
