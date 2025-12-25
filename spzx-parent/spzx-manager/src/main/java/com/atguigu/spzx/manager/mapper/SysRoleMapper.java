package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    //角色列表的查询方法
    List<SysRole> findByPage(SysRoleDto sysRoleDto);

    //添加角色方法
    void save(SysRole sysRole);

    //修改角色方法
    void update(SysRole sysRole);

    //删除角色方法
    void delete(Long roleId);
}
