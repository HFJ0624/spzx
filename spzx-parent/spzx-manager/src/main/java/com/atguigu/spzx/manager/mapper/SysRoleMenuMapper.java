package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.AssignMenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMenuMapper {

    //查询当前角色的菜单数据
    List<Long> findSysRoleMenuByRoleId(Long roleId);

    //删除角色之前分配过菜单数据
    void deleteByRoleId(Long roleId);

    //保存分配数据
    void doAssign(AssignMenuDto assignMenuDto);
}
