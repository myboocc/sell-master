/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.controller;

import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 卖家端
 * @author hongcj
 * @version V1.0
 * @since 2017-08-17 17:41
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 订单详情
     * @param page 当前页
     * @param size 每页数目
     * @param map
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
        @RequestParam(value = "size",defaultValue = "10") Integer size,
        Map<String,Object> map){
        PageRequest pageRequest = new PageRequest(page - 1 ,size);
        Page<OrderDTO> list = orderService.findList(pageRequest);
        map.put("list",list);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/list",map);
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @RequestMapping("/cancel")
    public ModelAndView cancel(@RequestParam String orderId,
                    Map<String,Object> map){
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            log.error("【卖家端取消订单】 发生异常{}",e);
            map.put("errorMsg", e.getMessage());
            //跳转到列表页
            map.put("redirectUrl","/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("errorMsg", ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        //跳转到列表页
        map.put("redirectUrl","/seller/order/list");
        return new ModelAndView("common/success");
    }

    /**
     * 订单详情
     * @param orderId
     * @param map
     * @return
     */
    @RequestMapping("/detail")
    public ModelAndView detail(@RequestParam String orderId,
        Map<String,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try{
            orderDTO = orderService.findOne(orderId);
        }catch (SellException e){
            log.error("【卖家端获取订单详情】 发生异常{}",e);
            map.put("errorMsg", e.getMessage());
            //跳转到列表页
            map.put("redirectUrl","/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/detail",map);
    }

    /**
     * 完结订单
     * @param orderId
     * @param map
     * @return
     */
    @RequestMapping("/finish")
    public ModelAndView finish(@RequestParam String orderId,
        Map<String,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try{
            orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            log.error("【卖家端完结订单】 发生异常{}",e);
            map.put("errorMsg",e.getMessage());
            //跳转到列表页
            map.put("redirectUrl","/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("errorMsg", ResultEnum.ORDER_FINISH_SUCCESS.getMsg());
        //跳转到列表页
        map.put("redirectUrl","/seller/order/list");
        return new ModelAndView("common/success",map);
    }
}
