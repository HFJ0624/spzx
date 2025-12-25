package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.manager.mapper.SysMenuMapper;
import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.manager.utils.MenuHelper;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 作者:hfj
 * 功能:
 * 日期: 2025/12/25 20:41
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    //菜单列表
    @Override
    public List<SysMenu> findNodes() {
        //1.查询所有菜单,返回list集合
        List<SysMenu> sysMenuList = sysMenuMapper.findAll();
        if (CollectionUtils.isEmpty(sysMenuList)){
            return null;
        }

        //2.调用工具类,把list集合封装成前端要求的数据格式
        List<SysMenu> treeList = MenuHelper.buildTree(sysMenuList);

        return treeList;
    }

    //添加菜单
    @Override
    public void save(SysMenu sysMenu) {
        sysMenuMapper.save(sysMenu);
    }

    //修改菜单
    @Override
    public void update(SysMenu sysMenu) {
        sysMenuMapper.update(sysMenu);
    }

    //删除菜单
    @Override
    public void removeById(Long id) {
        //根据当前菜单id,查询是否包含子菜单
        int count = sysMenuMapper.countByParentId(id);

        //count 大于0 包含子菜单
        if (count > 0){
            throw new GuiguException(ResultCodeEnum.NODE_ERROR);
        }

        //不存在子菜单直接删除
        sysMenuMapper.deleteById(id);
    }
}
