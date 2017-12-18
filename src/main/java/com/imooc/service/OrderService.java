/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.service;

import com.imooc.dto.OrderDTO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 订单Service
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-07-19 13:53
 */
public interface OrderService {
    /*创建订单*/
    OrderDTO create(OrderDTO orderDTO);

    /*查询单个订单*/
    OrderDTO findOne(String orderId);

    /* 查询订单列表 */
    Page<OrderDTO> findList(String buyerOpenId, Pageable pageable);

    /* 取消订单*/
    OrderDTO cancel(OrderDTO orderDTO);

    /* 完结订单*/
    OrderDTO finish(OrderDTO orderDTO);

    /* 支付订单 */
    OrderDTO paid(OrderDTO orderDTO);

    /* 分页查询订单列表 */
    Page<OrderDTO> findList(Pageable pageable);
}
