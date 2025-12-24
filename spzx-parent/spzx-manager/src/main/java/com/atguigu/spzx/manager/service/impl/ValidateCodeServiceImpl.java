package com.atguigu.spzx.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.atguigu.spzx.manager.service.ValidateCodeService;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 作者:hfj
 * 功能:生成图片实现类
 * 日期: 2025/12/24 16:37
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //生成图片验证码
    @Override
    public ValidateCodeVo generateValidateCode() {

        //1.通过工具类生成图片验证码
        //参数：宽  高  验证码位数 干扰线数量
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 20);
        String codeValue = circleCaptcha.getCode();//生成4位验证码的值
        String imageBase64 = circleCaptcha.getImageBase64();//返回图片验证码,进行base64编码

        //2.把验证码存储到redis里面，设置redis的key:uuid,value:验证码值，并且设置过期时间
        String key = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("user:validate" + key,codeValue,5, TimeUnit.MINUTES);//五分钟有效

        //3.返回validateCodeVo对象
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(key);//redis存储数据的key
        validateCodeVo.setCodeValue("data:image/png;base64," + imageBase64);

        return validateCodeVo;
    }
}
