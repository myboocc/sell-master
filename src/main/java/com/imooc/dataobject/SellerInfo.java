/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.dataobject;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

/**
 * TODO
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-08-21 11:15
 */
@Entity
@Data
@DynamicUpdate
public class SellerInfo {
    @Id
    private String id;

    private String username;

    private String password;

    private String openid;
}
