/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.service;

import com.imooc.dataobject.SellerInfo;

/**
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-08-21 13:22
 */
public interface SellerService {
    SellerInfo findSellerInfoByOpenid(String openid);
}
