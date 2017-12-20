package com.imooc.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.imooc.VO.ResultVO;
import com.imooc.constant.CookieConstant;
import com.imooc.constant.RedisConstant;
import com.imooc.dataobject.User;
import com.imooc.utils.CookieUtil;
import com.imooc.utils.ResultVOUtil;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	private String TOKEN = "Admin-Token";
	Gson gson = new Gson();

	@GetMapping("/login")
    public ResultVO<Map<String,String>> login(String username, String password, HttpServletResponse response){
		System.out.println(username);
		System.err.println(password);
		String token = UUID.randomUUID().toString();
		//3. 将token写入cookie
        CookieUtil.set(response, TOKEN, token, RedisConstant.EXPIRE);
        String role[] = {"admin"};
        User user = new User(role, "admin", "我是超级管理员", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif", "Super Admin");
        return ResultVOUtil.success(user);
    }
	
	@GetMapping("/userInfo")
    public ResultVO<Map<String,String>> getUserInfo(String token){
		System.out.println(token);
		String role[] = {"admin"};
		User user = new User(role, "admin", "我是超级管理员", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif", "Super Admin");
//		String user = "{ role: ['admin'], token: 'admin', introduction: '我是超级管理员', avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', name: 'Super Admin' }";
        return ResultVOUtil.success(user);
    }
	
}
