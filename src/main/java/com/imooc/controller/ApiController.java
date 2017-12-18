package com.imooc.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.VO.ResultVO;
import com.imooc.constant.CookieConstant;
import com.imooc.constant.RedisConstant;
import com.imooc.utils.CookieUtil;
import com.imooc.utils.ResultVOUtil;

@RestController
@RequestMapping("/api")
public class ApiController {

	@GetMapping("/login")
    public ResultVO<Map<String,String>> login(String username, String password, HttpServletResponse response){
		System.out.println(username);
		System.err.println(password);
		String token = UUID.randomUUID().toString();
		//3. 将token写入cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, RedisConstant.EXPIRE);
        return ResultVOUtil.success();
    }
	
	@GetMapping("/userInfo")
    public ResultVO<Map<String,String>> getUserInfo(String token){
		System.out.println(token);
        return ResultVOUtil.success();
    }
	
}
