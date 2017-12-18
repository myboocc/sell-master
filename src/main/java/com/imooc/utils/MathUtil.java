/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.utils;

/**
 * @author hongcj
 * @version V1.0
 * @since 2017-08-17 14:22
 */
public class MathUtil {
    private static final Double MONEY_RANGE = 0.01;

    //比较金额是否相同，这里少于0.01则认为两个金额相同
    public static boolean equal(Double a, Double b) {
        double abs = Math.abs(a - b);
        if (abs < MONEY_RANGE) {
            return true;
        } else {
            return false;
        }
    }
}
