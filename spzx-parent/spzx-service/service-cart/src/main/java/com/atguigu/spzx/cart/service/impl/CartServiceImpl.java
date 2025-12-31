package com.atguigu.spzx.cart.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.cart.service.CartService;
import com.atguigu.spzx.feign.product.ProductFeignClient;
import com.atguigu.spzx.model.entity.h5.CartInfo;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 作者:hfj
 * 功能:
 * 日期: 2025/12/31 10:13
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public void addToCart(Long skuId, Integer skuNum) {
        //1.必须是登录状态,获取当爱情那登录用户id(作为redis的hash类型的key值)
        //从ThreadLocal获取用户信息即可
        Long userId = AuthContextUtil.getUserInfo().getId();
        //构建hash类型key的名称
        String cartKey = this.getCartKey(userId);

        //2.购物车存放在redis里面
        //从redis里面获取购物车数据,根据用户id + skuId获取(hash类型key + field)
        Object cartInfoObject = redisTemplate.opsForHash().get(cartKey, String.valueOf(skuId));

        CartInfo cartInfo = null;
        //3.如果购物车存在添加商品,把商品数量相加
        if (cartInfoObject != null){ //添加到购物车商品已经存在的,把商品数量相加
            //cartInfoObject -> cartInfo
            cartInfo = JSON.parseObject(cartInfoObject.toString(), CartInfo.class);
            //数量相加
            cartInfo.setSkuNum(cartInfo.getSkuNum() + skuNum);
            //设置属性,购物车商品选中状态
            cartInfo.setIsChecked(1);
            cartInfo.setUpdateTime(new Date());
        }else {
            //4.如果购物车没有添加商品,直接商品添加购物车(添加到redis里面)
            //使用远程调用实现:nacos + openFeign实现 根据skuId获取商品sku信息
            cartInfo = new CartInfo();

            //远程调用实现:根据skuId获取商品sku信息
            ProductSku productSku = productFeignClient.getBySkuId(skuId);
            //设置相关数据到cartInfo对象里面
            cartInfo.setCartPrice(productSku.getSalePrice());
            cartInfo.setSkuNum(skuNum);
            cartInfo.setSkuId(skuId);
            cartInfo.setUserId(userId);
            cartInfo.setImgUrl(productSku.getThumbImg());
            cartInfo.setSkuName(productSku.getSkuName());
            cartInfo.setIsChecked(1);
            cartInfo.setCreateTime(new Date());
            cartInfo.setUpdateTime(new Date());
        }

        //添加到redis里面
        redisTemplate.opsForHash().put(cartKey , String.valueOf(skuId) , JSON.toJSONString(cartInfo));
    }

    //查询购物车
    @Override
    public List<CartInfo> getCartList() {
        //1.构建查询redis里面的key值,根据当前userId
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);

        //2.根据key从redis里面hash类型获取所有value值 cartInfo
        List<Object> valueList = redisTemplate.opsForHash().values(cartKey);

        if (!CollectionUtils.isEmpty(valueList)){
            List<CartInfo> infoList = valueList.stream().map(cartInfo -> JSON.parseObject(cartInfo.toString(), CartInfo.class))
                    .sorted((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()))
                    .collect(Collectors.toList());
            return infoList;
        }
        return new ArrayList<>();
    }

    private String getCartKey(Long userId) {
        //定义key user:cart:userId
        return "user:cart:" + userId;
    }
}
