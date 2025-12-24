package com.atguigu.spzx.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.manager.mapper.SysUserMapper;
import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 作者:hfj
 * 功能:系统用户功能模块
 * 日期: 2025/12/24 10:18
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //用户登录
    @Override
    public LoginVo login(LoginDto loginDto) {

        //1.获取提交的用户名，loginDto获取
        String userName = loginDto.getUserName();

        //2.根据用户名查询数据库表 sys_user表
        SysUser sysUser = sysUserMapper.selectUserInfoByUserName(userName);

        //3.根据用户名查找对应的用户信息，找不到则返回错误信息
        if (sysUser == null){
            throw new RuntimeException("用户名不存在");
        }

        //4.用户存在
        //5.获取输入的密码和数据的密码，对输入的密码进行加密并和数据库的密码进行比较，判断是否一致
        String database_password = sysUser.getPassword();
        String input_password = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());

        //比较
        if (!input_password.equals(database_password)){
            throw new RuntimeException("密码不正确");
        }

        //6.登录成功，生成用户唯一标识token,把登录成功用户信息存入到redis里面
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("user:login:" + token , JSON.toJSONString(sysUser) , 7 , TimeUnit.DAYS);

        //7.返回loginVo
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);

        return loginVo;
    }
}
