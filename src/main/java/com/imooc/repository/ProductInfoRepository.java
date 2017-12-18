/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.repository;

import com.imooc.dataobject.ProductInfo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 商品信息dao
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-07-14 11:17
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>{
    /*根据上架状态查询商品*/
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
