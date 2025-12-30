package com.atguigu.spzx.user.service;

public interface SmsService {

    //发送短信验证码功能
    void sendValidateCode(String phone);
}
