package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper {

    //1.查询所有菜单,返回list集合
    List<SysMenu> findAll();

    //添加菜单
    void save(SysMenu sysMenu);

    //修改菜单
    void update(SysMenu sysMenu);

    //根据当前菜单id,查询是否包含子菜单
    int countByParentId(Long id);

    //不存在子菜单直接删除
    void deleteById(Long id);

    //根据用户id查询可以操作的菜单
    List<SysMenu> findMenusByUserId(Long userId);

    //获取当前添加菜单的父菜单
    SysMenu selectParentMenu(Long parentId);
}
