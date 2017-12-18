/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.form;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * 创建订单时参数
 * @author hongcj
 * @version V1.0
 * @since 2017-07-19 16:22
 */
@Data
public class OrderForm {
    /*姓名*/
    @NotEmpty(message = "姓名不能为空")
    private String name;
    /* 手机号码*/
    @NotEmpty(message = "手机号码不能为空")
    private String phone;
    /*地址*/
    @NotEmpty(message = "地址不能为空")
    private String address;
    /*用户的微信openid*/
    @NotEmpty(message = "微信openId不能为空")
    private String openid;
    //购物车
    @NotEmpty(message = "购物车不能为空")
    private String items;

}
