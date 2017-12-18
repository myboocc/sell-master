/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.service;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 商品信息
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-07-17 09:59
 */
public interface ProductInfoServcie {
    ProductInfo findOne(String productId);

    /*查询所有上架的列表*/
    List<ProductInfo> findUpAll();

    /*分页查询所有商品*/
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /*减库存*/
    void decreaseStock(List<CartDTO> cartDTOList);

    /*加库存*/
    void increaseStock(List<CartDTO> cartDTOList);

    /*上架*/
    ProductInfo onSale(String productId);

    /*下架*/
    ProductInfo OffSale(String productId);
}
