package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.system.SysMenuVo;

import java.util.List;

public interface SysMenuService {

    //菜单列表
    List<SysMenu> findNodes();

    //添加菜单
    void save(SysMenu sysMenu);

    //修改菜单
    void update(SysMenu sysMenu);

    //删除菜单
    void removeById(Long id);

    //查询用户可以操作的菜单
    List<SysMenuVo> findMenusByUserId();
}
