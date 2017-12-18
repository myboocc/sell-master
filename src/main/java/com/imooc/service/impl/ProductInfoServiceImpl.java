/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.ProductInfoRepository;
import com.imooc.service.ProductInfoServcie;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-07-17 10:04
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoServcie {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        ProductInfo productInfo = null;
        for (CartDTO cartDTO: cartDTOList){
            productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //判断库存是否足够
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(result < 0){
                //库存不足
                throw new SellException((ResultEnum.PRODUCT_STOCK_ERROR));
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        ProductInfo productInfo = null;
        for (CartDTO cartDTO: cartDTOList){
            productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //判断库存是否足够
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = productInfoRepository.findOne(productId);
        if(Objects.isNull(productInfo)){
            //商品不存在
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(ProductStatusEnum.DOWN.getCode().equals(productInfo.getProductStatus())){
            //将商品状态改为上架
            productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
            productInfoRepository.save(productInfo);
        }else{
            //商品状态不正确
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        return productInfo;
    }

    @Override
    public ProductInfo OffSale(String productId) {
        ProductInfo productInfo = productInfoRepository.findOne(productId);
        if(Objects.isNull(productInfo)){
            //商品不存在
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(ProductStatusEnum.UP.getCode().equals(productInfo.getProductStatus())){
            //将商品状态改为下架
            productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
            productInfoRepository.save(productInfo);
        }else{
            //商品状态不正确
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        return productInfo;
    }
}
