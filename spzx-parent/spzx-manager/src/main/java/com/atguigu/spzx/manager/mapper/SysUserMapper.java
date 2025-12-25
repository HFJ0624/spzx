package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {

    //2.根据用户名查询数据库表 sys_user表
    SysUser selectUserInfoByUserName(String userName);

    //用户条件分页查询接口
    List<SysUser> findByPage(SysUserDto sysUserDto);

    //用户的添加功能
    void save(SysUser sysUser);

    //用户的修改功能
    void update(SysUser sysUser);

    //用户的删除功能
    void delete(Long userId);
}
