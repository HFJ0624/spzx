package com.atguigu.spzx.pay.service;

import com.atguigu.spzx.model.entity.pay.PaymentInfo;

public interface PaymentInfoService {

    //保存支付记录
    PaymentInfo savePaymentInfo(String orderNo);
}
