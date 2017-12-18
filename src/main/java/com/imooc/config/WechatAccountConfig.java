/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 微信账户相关配置
 * @author hongcj
 * @version V1.0
 * @since 2017-08-09 10:54
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    /**
     * 公众号APPID(自己的测试号)
     */
    private String mpAppid;

    /**
     * 公众号APPSercert（自己的测试号）
     */
    private String mpAppSecert;



    /**
     * 公众号APPID(借用的的公众号)
     */
    private String mcMpAppid;

    /**
     * 公众号APPSercert(借用的的公众号)
     */
    private String mcMpAppSecert;

    /**
     * 开放平台openid
     */
    private String openAppid;

    /**
     * 开放平台secert
     */
    private String openAppSecert;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 支付完成后的异步通知地址.(很重要)
     */
    private String notifyUrl;

}
