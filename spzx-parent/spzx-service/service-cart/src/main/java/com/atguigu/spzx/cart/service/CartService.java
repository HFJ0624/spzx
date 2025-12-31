package com.atguigu.spzx.cart.service;

import com.atguigu.spzx.model.entity.h5.CartInfo;

import java.util.List;

public interface CartService {

    //添加购物车
    void addToCart(Long skuId, Integer skuNum);

    //查询购物车
    List<CartInfo> getCartList();

    //删除购物车商品
    void deleteCart(Long skuId);

    //更新购物车商品选中状态
    void allCheckCart(Long skuId,Integer isChecked);
}
