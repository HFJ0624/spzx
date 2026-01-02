package com.atguigu.spzx.user.service;

import com.atguigu.spzx.model.entity.user.UserAddress;

import java.util.List;

public interface UserAddressService {

    //获取用户地址列表
    List<UserAddress> findUserAddressList();
}
