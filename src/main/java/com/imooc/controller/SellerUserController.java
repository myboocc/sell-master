/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.controller;

import com.imooc.config.ProjectUrlConfig;
import com.imooc.constant.CookieConstant;
import com.imooc.constant.RedisConstant;
import com.imooc.dataobject.SellerInfo;
import com.imooc.enums.ResultEnum;
import com.imooc.service.SellerService;
import com.imooc.utils.CookieUtil;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户登录登出
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-08-21 14:47
 */
@Controller
@Slf4j
@RequestMapping("/seller")
public class SellerUserController {
    @Autowired
    private SellerService sellerService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(value = "openid", required = false) String openid,
        HttpServletResponse response,Map<String,Object> map){
        //1. openid去和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if(sellerInfo == null){
            //数据库中未找到，则跳转到错误界面
            map.put("msg", ResultEnum.LOGIN_FAIL.getMsg());
            map.put("redirectUrl", "/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        //2. 将token写入redis
        String token = UUID.randomUUID().toString();
        //key,value,过期时间
        redisTemplate.opsForValue().set(RedisConstant.TOKEN_PREFIX + token,openid, RedisConstant.EXPIRE,TimeUnit.SECONDS);
        //3. 将token写入cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, RedisConstant.EXPIRE);
        //4.设置成功后跳转列表页
        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/seller/order/list");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
        HttpServletResponse response,
        Map<String,Object> map){
        //1. 从cookie里查询,获取token
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        //2. 删除redis里的token
        if(cookie != null){
            redisTemplate.opsForValue().getOperations().delete(CookieConstant.TOKEN);
        }
        //3. 清除cookie里的token信息
        CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        //4.清除成功后跳转成功界面
        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMsg());
        map.put("redirectUrl", "/seller/order/list");
        return new ModelAndView("common/success", map);
    }
}
