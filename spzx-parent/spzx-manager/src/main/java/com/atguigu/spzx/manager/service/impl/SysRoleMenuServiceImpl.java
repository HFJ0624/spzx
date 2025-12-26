package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.SysRoleMenuMapper;
import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.manager.service.SysRoleMenuService;
import com.atguigu.spzx.model.dto.system.AssignMenuDto;
import com.atguigu.spzx.model.entity.system.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者:hfj
 * 功能:
 * 日期: 2025/12/26 8:07
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysMenuService sysMenuService;

    //查询所有菜单和查询角色分配过菜单id列表
    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {

        //1.查询所有菜单数据
        List<SysMenu> sysMenuList = sysMenuService.findNodes();

        //2.查询当前角色的菜单数据
        List<Long> roleMenuIds = sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId);

        //3.将数据存到map返回
        HashMap<String, Object> map = new HashMap<>();
        map.put("sysMenuList" , sysMenuList);
        map.put("roleMenuIds" , roleMenuIds);

        return map;
    }

    //保存角色分配菜单数据
    @Transactional
    @Override
    public void doAssign(AssignMenuDto assignMenuDto) {

        //删除角色之前分配过菜单数据
        sysRoleMenuMapper.deleteByRoleId(assignMenuDto.getRoleId());

        //保存分配数据
        List<Map<String, Number>> menuInfo = assignMenuDto.getMenuIdList();
        //角色分配了菜单
        if (menuInfo != null && menuInfo.size() > 0){
            sysRoleMenuMapper.doAssign(assignMenuDto);
        }
    }
}
