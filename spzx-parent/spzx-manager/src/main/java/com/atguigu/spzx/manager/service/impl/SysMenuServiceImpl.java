package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.manager.mapper.SysMenuMapper;
import com.atguigu.spzx.manager.mapper.SysRoleMenuMapper;
import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.manager.utils.MenuHelper;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.SysMenuVo;
import com.atguigu.spzx.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
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

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

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

        //新添加子菜单,就要把该菜单对应的父级菜单设置为半开
        updateSysRoleMenuIsHalf(sysMenu);
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

    //查询用户可以操作的菜单
    @Override
    public List<SysMenuVo> findMenusByUserId() {
        //1.获取当前用户id
        SysUser sysUser = AuthContextUtil.get();
        Long userId = sysUser.getId();

        //2.根据用户id查询可以操作的菜单
        List<SysMenu> sysMenuList = sysMenuMapper.findMenusByUserId(userId);

        //3.封装要求数据格式,返回
        List<SysMenu> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);
        List<SysMenuVo> sysMenuVoList = this.buildMenus(sysMenuTreeList);

        return sysMenuVoList;
    }

    // 将List<SysMenu>对象转换成List<SysMenuVo>对象
    private List<SysMenuVo> buildMenus(List<SysMenu> menus) {
        List<SysMenuVo> sysMenuVoList = new LinkedList<SysMenuVo>();
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }

    private void updateSysRoleMenuIsHalf(SysMenu sysMenu) {
        //获取当前添加菜单的父菜单
        SysMenu parentMenu = sysMenuMapper.selectParentMenu(sysMenu.getParentId());
        if(parentMenu != null) {
            //将该id的菜单设置为半开 isHalf = 1
            sysRoleMenuMapper.updateSysRoleMenuIsHalf(parentMenu.getId());
            //递归调用
            updateSysRoleMenuIsHalf(parentMenu);
        }
    }
}
