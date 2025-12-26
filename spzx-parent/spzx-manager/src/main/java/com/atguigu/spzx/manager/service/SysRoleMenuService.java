package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.AssignMenuDto;

import java.util.Map;

public interface SysRoleMenuService {

    //查询所有菜单和查询角色分配过菜单id列表
    Map<String, Object> findSysRoleMenuByRoleId(Long roleId);

    //保存角色分配菜单数据
    void doAssign(AssignMenuDto assignMenuDto);
}
