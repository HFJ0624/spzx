package com.atguigu.spzx.user.mapper;

import com.atguigu.spzx.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {

    //校验用户名不能重复
    UserInfo getByUsername(String username);

    //保存用户信息
    void save(UserInfo userInfo);
}
