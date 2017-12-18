/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 *
 * 商品(包含类目)
 * @author hongcj
 * @version V1.0
 * @since 2017-07-17 11:24
 */
@Data
public class ProductVO implements Serializable{

    private static final long serialVersionUID = -6648827451746590654L;

    @JsonProperty("name")
    private String categoryname;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
