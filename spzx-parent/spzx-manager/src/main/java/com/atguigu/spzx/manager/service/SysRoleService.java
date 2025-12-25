package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.github.pagehelper.PageInfo;

public interface SysRoleService {

    //角色列表的查询方法
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit);

    //添加角色方法
    void saveSysRole(SysRole sysRole);

    //修改角色方法
    void updateSysRole(SysRole sysRole);

    //删除角色方法
    void deleteById(Long roleId);
}
