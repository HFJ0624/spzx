package com.atguigu.spzx.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.model.dto.h5.ProductSkuDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.entity.product.ProductDetails;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.vo.h5.ProductItemVo;
import com.atguigu.spzx.product.mapper.ProductDetailsMapper;
import com.atguigu.spzx.product.mapper.ProductMapper;
import com.atguigu.spzx.product.mapper.ProductSkuMapper;
import com.atguigu.spzx.product.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者:hfj
 * 功能:
 * 日期: 2025/12/29 17:26
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    //根据销量排序,获取前10条记录
    @Override
    public List<ProductSku> findProductSkuBySale() {
        List<ProductSku> productSkuList = productSkuMapper.findProductSkuBySale();
        return productSkuList;
    }

    //商品的分页查询
    @Override
    public PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto) {
        PageHelper.startPage(page, limit);
        List<ProductSku> productSkuList = productSkuMapper.findByPage(productSkuDto);
        return new PageInfo<>(productSkuList);
    }

    //商品的详情功能
    @Override
    public ProductItemVo item(Long skuId) {
        //1.创建vo对象,用于封装最终数据
        ProductItemVo productItemVo = new ProductItemVo();

        //2.根据skuId获取sku信息
        ProductSku productSku = productSkuMapper.getById(skuId);

        //3.根据第二部获取sku,从sku获取productId,获取商品信息
        Long productId = productSku.getProductId();
        Product product = productMapper.getById(productId);

        //4.根据productId,获取商品详情信息
        ProductDetails productDetails = productDetailsMapper.getByProductId(productId);

        //5.封装map集合->商品规格对应商品skuId信息
        Map<String,Object> skuSpecValueMap = new HashMap<>();
        //根据商品id获取商品所有sku列表
        List<ProductSku> productSkuList = productSkuMapper.findByProductId(productId);

        //建立sku规格与skuId对应关系
        productSkuList.forEach(item -> {
            skuSpecValueMap.put(item.getSkuSpec(), item.getId());
        });

        //6.把需要数据封装到productItemVo里面
        productItemVo.setProductSku(productSku);
        productItemVo.setProduct(product);
        //详情图片封装 list集合
        productItemVo.setDetailsImageUrlList(Arrays.asList(productDetails.getImageUrls().split(",")));
        //轮播图封装 list集合
        productItemVo.setSliderUrlList(Arrays.asList(product.getSliderUrls().split(",")));
        //商品规格信息
        productItemVo.setSpecValueList(JSON.parseArray(product.getSpecValue()));
        productItemVo.setSkuSpecValueMap(skuSpecValueMap);
        return productItemVo;
    }

    //远程调用:根据skuId返回sku信息
    @Override
    public ProductSku getBySkuId(Long skuId) {
        ProductSku productSku = productSkuMapper.getById(skuId);
        return productSku;
    }
}
