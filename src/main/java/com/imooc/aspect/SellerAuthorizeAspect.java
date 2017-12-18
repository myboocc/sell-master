///*
// * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
// * This software is the confidential and proprietary information of GuaHao Company. 
// * ("Confidential Information"). 
// * You shall not disclose such Confidential Information and shall use it only 
// * in accordance with the terms of the license agreement you entered into with GuaHao.com.
// */
//package com.imooc.aspect;
//
//import com.imooc.constant.CookieConstant;
//import com.imooc.constant.RedisConstant;
//import com.imooc.exception.SellerAuthorizeException;
//import com.imooc.utils.CookieUtil;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * 登录权限验证
// * @author hongcj
// * @version V1.0
// * @since 2017-08-21 15:34
// */
//@Aspect
//@Component
//@Slf4j
//public class SellerAuthorizeAspect {
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    // 定义切面,拦截出用户登录外的卖家端请求
//    @Pointcut("execution(public * com.imooc.controller.Seller*.*(..))"
//        + "&&!execution(public * com.imooc.controller.SellerUserController.*(..))")
//    public void verify(){}
//
//    @Before(value = "verify()")
//    public void doVerify(){
//        //获取cookie
//        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
//        if(cookie == null){
//            log.warn("【登录校验】Cookie中查不到token");
//            throw new SellerAuthorizeException();
//        }
//        //判断reids里是否有这个cookie
//        String tokenValue = redisTemplate.opsForValue().get(RedisConstant.TOKEN_PREFIX + cookie.getValue());
//        if(StringUtils.isEmpty(tokenValue)){
//            log.warn("【登录校验】Redis中查不到token");
//            throw new SellerAuthorizeException();
//        }
//    }
//}
