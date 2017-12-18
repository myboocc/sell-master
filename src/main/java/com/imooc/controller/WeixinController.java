/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.controller;

import com.imooc.config.ProjectUrlConfig;
import com.imooc.converter.WxMappingJackson2HttpMessageConverter;
import com.imooc.dto.WeChatAccessTokenDTO;
import com.lly835.bestpay.rest.type.Get;

import java.net.URLEncoder;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * 微信授权
 * @author hongcj
 * @version V1.0
 * @since 2017-08-08 19:11
 */
@Controller
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    public final String APPID = "wxfc26418b74dcb0a8";  //服务号APPID
    public final String APPSECRET = "fadb448b94aa080266315a3607b689ea"; //服务号APPSecret
    public final String SCOPE = "snsapi_base"; //应用授权作用域
    public final String STATE = "state"; //跳转携带的参数

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/getOpenId")
    public String getOpenId(){

        String redirectUrI = projectUrlConfig.getWechatMpAuthorize() + "/weixin/auth";

        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APPID
            + "&redirect_uri=" + URLEncoder.encode(redirectUrI) + "&response_type=code&scope="
            + SCOPE + "&state=" + STATE + "#wechat_redirect";

        return "redirect:" + url;
    }

    @GetMapping("/auth")
    public ModelAndView auth(@RequestParam("code") String code,Map<String,Object> map){
        log.info("进入auth方法");
        log.info("code={}",code);
        //获取code后调用获取token接口，获取token，在获取token接口的返回集里有我们需要的openId
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID + "&secret=" + APPSECRET
            + "&code=" + code + "&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
        WeChatAccessTokenDTO response = restTemplate.getForObject(url, WeChatAccessTokenDTO.class);
        log.info("response={}", response);
        map.put("response",response);
        return new ModelAndView("auth/openid",map);
    }
}

