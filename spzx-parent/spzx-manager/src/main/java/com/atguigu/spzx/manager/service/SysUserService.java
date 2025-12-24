package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.vo.system.LoginVo;

public interface SysUserService {

    //用户登录
    LoginVo login(LoginDto loginDto);
}
