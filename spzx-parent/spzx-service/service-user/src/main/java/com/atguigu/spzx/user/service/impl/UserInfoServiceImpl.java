package com.atguigu.spzx.user.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.model.dto.h5.UserLoginDto;
import com.atguigu.spzx.model.dto.h5.UserRegisterDto;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.h5.UserInfoVo;
import com.atguigu.spzx.user.mapper.UserInfoMapper;
import com.atguigu.spzx.user.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 作者:hfj
 * 功能:
 * 日期: 2025/12/30 20:56
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisTemplate<String , String> redisTemplate;

    //用户注册
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(UserRegisterDto userRegisterDto) {
        //1.从userRegisterDto获取数据
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String nickName = userRegisterDto.getNickName();
        String code = userRegisterDto.getCode(); //验证码

        //判断是否为空
        if(StringUtils.isEmpty(username) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(nickName) ||
                StringUtils.isEmpty(code)) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }

        //2.验证码校验
        //2.1从redis获取发送验证码
        String redisCode = redisTemplate.opsForValue().get(username);

        //2.2获取输入的验证码,进行校验
        if (!redisCode.equals(code)){
            throw new GuiguException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        //3.校验用户名不能重复
        UserInfo userInfo = userInfoMapper.getByUsername(username);
        if(userInfo != null) { //存在相同的用户名
            throw new GuiguException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        //4.封装添加的数据,调用方法添加到数据库
        userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setNickName(nickName);
        userInfo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userInfo.setPhone(username);
        userInfo.setStatus(1);
        userInfo.setSex(0);
        userInfo.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        userInfoMapper.save(userInfo);

        //5.从redis删除发送的验证码
        redisTemplate.delete(username);
    }

    //用户登录
    @Override
    public String login(UserLoginDto userLoginDto) {
        //1.校验参数
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();

        //校验参数
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }

        //2.查询数据库是否有改用户
        UserInfo userInfo = userInfoMapper.getByUsername(username);
        if (userInfo == null){
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }

        //3.校验密码
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!md5Password.equals(userInfo.getPassword())) {
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }

        //4.校验是否被禁用
        if(userInfo.getStatus() == 0) {
            throw new GuiguException(ResultCodeEnum.ACCOUNT_STOP);
        }

        //5.生成token,把用户信息放入到redis里面
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set("user:spzx:" + token, JSON.toJSONString(userInfo), 30, TimeUnit.DAYS);
        return token;
    }

    //获取当前登录用户信息
    @Override
    public UserInfoVo getCurrentUserInfo(String token) {
        //从redis里根据token获取信息
        String userInfoJSON = redisTemplate.opsForValue().get("user:spzx:" + token);

        if(StringUtils.isEmpty(userInfoJSON)) {
            throw new GuiguException(ResultCodeEnum.LOGIN_AUTH);
        }

        //把json字符串转成成UserInfo对象
        UserInfo userInfo = JSON.parseObject(userInfoJSON,UserInfo.class);
        UserInfoVo userInfoVo = new UserInfoVo();
        //把userInfo对象转换成userInfoVo
        BeanUtils.copyProperties(userInfo,userInfoVo);
        return userInfoVo ;
    }
}
