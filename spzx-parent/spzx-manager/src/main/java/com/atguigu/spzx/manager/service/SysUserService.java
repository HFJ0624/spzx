package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.AssignRoleDto;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.github.pagehelper.PageInfo;

public interface SysUserService {

    //用户登录
    LoginVo login(LoginDto loginDto);

    //获取当前的用户信息
    SysUser getUserInfo(String token);

    //用户退出
    void logout(String token);

    //用户条件分页查询接口
    PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto);

    //用户的添加功能
    void saveSysUser(SysUser sysUser);

    //用户的修改功能
    void updateSysUser(SysUser sysUser);

    //用户的删除功能
    void deleteById(Long userId);

    //用户分配角色并保存分配数据
    void doAssign(AssignRoleDto assignRoleDto);
}
