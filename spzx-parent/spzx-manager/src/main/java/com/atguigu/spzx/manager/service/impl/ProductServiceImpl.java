package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.ProductDetailsMapper;
import com.atguigu.spzx.manager.mapper.ProductMapper;
import com.atguigu.spzx.manager.mapper.ProductSkuMapper;
import com.atguigu.spzx.manager.service.ProductService;
import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.entity.product.ProductDetails;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 作者:hfj
 * 功能:
 * 日期: 2025/12/27 16:37
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    //条件分页查询
    @Override
    public PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto) {
        PageHelper.startPage(page,limit);
        List<Product> productList =  productMapper.findByPage(productDto);
        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        return pageInfo;
    }

    //添加商品信息
    @Transactional
    @Override
    public void save(Product product) {
        //1.保存商品基本信息->product表
        product.setStatus(0);
        product.setAuditStatus(0);
        productMapper.save(product);

        //2.获取商品sku列表集合,保存sku信息->product_sku表
        List<ProductSku> productSkuList = product.getProductSkuList();
        int i = 0;
        for (ProductSku productSku : productSkuList){
            //商品编号
            productSku.setSkuCode(product.getId() + "_" + i);

            // 设置商品id和名称
            productSku.setProductId(product.getId());
            productSku.setSkuName(product.getName() + productSku.getSkuSpec());

            // 设置销量
            productSku.setSaleNum(0);
            productSku.setStatus(0);

            //保存数据
            productSkuMapper.save(productSku);
        }

        //3.保存商品详情数据->product_details表
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.save(productDetails);
    }

    //根据商品id查询商品信息
    @Override
    public Product getById(Long id) {
        //1.根据id查询商品基本信息(product表)
        Product product = productMapper.findProductById(id);

        //2.根据id查询商品sku信息列表(product_sku表)
        List<ProductSku> productSkuList = productSkuMapper.findProductSkuByProductId(id);
        product.setProductSkuList(productSkuList);

        //3.根据id删除商品详情数据(product_details表)
        ProductDetails productDetails = productDetailsMapper.findProductDetailsById(product.getId());
        product.setDetailsImageUrls(productDetails.getImageUrls());

        return product;
    }

    //保存修改的数据
    @Transactional
    @Override
    public void updateById(Product product) {
        //1.修改商品基本数据
        productMapper.updateById(product);

        //2.修改商品的sku数据
        List<ProductSku> productSkuList = product.getProductSkuList();
        productSkuList.forEach(productSku -> {
            productSkuMapper.updateById(productSku);
        });

        //3.修改商品的详情数据
        ProductDetails productDetails = productDetailsMapper.findProductDetailsById(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.updateById(productDetails);
    }
}
