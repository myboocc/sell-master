/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.handle;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.imooc.VO.ResultVO;
import com.imooc.config.ProjectUrlConfig;
import com.imooc.exception.SellException;
import com.imooc.exception.SellerAuthorizeException;
import com.imooc.utils.ResultVOUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 卖家端登录异常统一处理
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-08-21 15:47
 */
@ControllerAdvice
public class SellExceptionHandler {
    @Autowired
    private ProjectUrlConfig projectUrlConfig;


    /**
     * 如果未登录，则跳转到扫码登录界面
     * http://sell.natapp4.cc/sell/wechat/qrAuthorize?returnUrl=http://sell.natapp4.cc/sell/seller/login
     */
    //拦截卖家端登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handleException(){
//        return new ModelAndView("redirect:"
//            .concat(projectUrlConfig.getWechatOpenAuthorize())
//            .concat("/sell/wechat/qrAuthorize")
//            .concat("?returnUrl=")
//            .concat(projectUrlConfig.getSell())
//            .concat("/sell/seller/login"));
        return new ModelAndView("common/loginTip");
    }


    @ExceptionHandler(value = SellException.class)
    @ResponseBody
//    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResultVO handSellException(SellException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }

}
