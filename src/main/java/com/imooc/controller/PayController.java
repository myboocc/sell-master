/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.controller;

import com.imooc.converter.WxMappingJackson2HttpMessageConverter;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import com.imooc.service.PayService;
import com.lly835.bestpay.model.PayResponse;

import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 * 微信支付
 * @author hongcj
 * @version V1.0
 * @since 2017-08-10 10:06
 */
@Controller
public class PayController {
    public static String PAY_URL = "http://sell.springboot.cn/sell/pay?openid=oTgZpwS5cH2oGp4cfGb4rZPF2dbY&";

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    /**
     * 借用公众号的回调地址
     * 请求示意图
     支付授权目录 -> 你的外网 -> 你的电脑,这里的openId为与所借公众号的openId相对应
     支付授权目录 http://sell.springboot.cn/sell/pay?openid=xxxxxxxxx
     你的外网 http://xxx.s1.natapp.cc/pay?openid=xxxxxxxxx
     你的电脑 http://127.0.0.1:8080/pay?openid=xxxxxxxxxx
     * @return
     */
    @GetMapping("/pay")
    @ResponseBody
    public ModelAndView pay(@RequestParam("orderId") String orderId,
        @RequestParam("returnUrl") String returnUrl,
        Map<String,Object> map){
        //1.查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(Objects.isNull(orderDTO)){
            //订单不存在
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl + "/#/order/" + orderId);
        return new ModelAndView("pay/create",map);
    }

    /**
     * 接收微信异步通知
     * @param notifyData
     * @return
     */
    @PostMapping("/pay/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        //商户处理异步通知
        payService.notify(notifyData);
        //告知微信处理结果
        return new ModelAndView("pay/success");
    }

}

