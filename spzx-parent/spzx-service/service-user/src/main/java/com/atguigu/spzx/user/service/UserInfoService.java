package com.atguigu.spzx.user.service;

import com.atguigu.spzx.model.dto.h5.UserRegisterDto;

public interface UserInfoService {

    //用户注册
    void register(UserRegisterDto userRegisterDto);
}
