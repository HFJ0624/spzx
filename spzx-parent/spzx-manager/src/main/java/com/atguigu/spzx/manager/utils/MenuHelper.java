package com.atguigu.spzx.manager.utils;

import com.atguigu.spzx.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:hfj
 * 功能:构建树形菜单的工具类
 * 日期: 2025/12/25 20:58
 */
public class MenuHelper {

    /**
     * 递归实现封装过程
     * @param sysMenuList 所有菜单集合
     * @return 返回封装好的树形结构
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        //创建list集合,用于封装最终数据
        List<SysMenu> trees = new ArrayList<>();

        //遍历所有菜单集合
        for (SysMenu sysMenu : sysMenuList) {
            //找到递归入口, 第一层菜单parentId = 0
            if (sysMenu.getParentId().longValue() == 0) {

                //根据第一层,找下一层数据,使用递归实现
                //方法里面传递两个参数:第一个参数当前第一层菜单,第二个参数为所有菜单集合
                trees.add(findChildren(sysMenu,sysMenuList));//封装他的子菜单
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param sysMenuList 所有菜单集合
     * @return 返回所有子菜单
     */
    public static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        //SysMenu有属性List<SysMenu> children,用来封装下一层数据
        sysMenu.setChildren(new ArrayList<>());

        //递归查询
        //sysMenu的id值 和 sysMenuList中的parentId值是否相同
        for (SysMenu it : sysMenuList) {
            //判断id 和 parentId是否相同
            if(sysMenu.getId().longValue() == it.getParentId().longValue()) {
                //it为下层封装数据,进行封装
                sysMenu.getChildren().add(findChildren(it,sysMenuList));
            }
        }
        return sysMenu;
    }
}
