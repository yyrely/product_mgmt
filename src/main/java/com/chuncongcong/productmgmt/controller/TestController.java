package com.chuncongcong.productmgmt.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuncongcong.productmgmt.config.modelMapper.ModelMapperOperation;
import com.chuncongcong.productmgmt.dao.UserInfoDao;
import com.chuncongcong.productmgmt.exception.ServiceException;
import org.modelmapper.ModelMapper;

/**
 * @author HU
 * @date 2019/12/18 17:16
 */

@RestController
@RequestMapping("/test")
public class TestController {

	private String token;

	private String refreshToken;


	@GetMapping("/token")
	public Object token() {
		Map<String, String> map = new HashMap<>();
		String token = UUID.randomUUID().toString();
		this.token = token;
		String refreshToken = UUID.randomUUID().toString();
		this.refreshToken = refreshToken;
		map.put("token", token);
		map.put("refresh_token", refreshToken);
		return map;
	}

	@GetMapping("/refresh/token")
	public Object refreshToken(String refreshToken) {
		if(!refreshToken.equals(this.refreshToken)) {
			throw new ServiceException("refresh_token 已过期请检查");
		}
		Map<String, String> map = new HashMap<>();
		String token = UUID.randomUUID().toString();
		this.token = token;
		String newRefreshToken = UUID.randomUUID().toString();
		this.refreshToken = newRefreshToken;
		map.put("token", token);
		map.put("refresh_token", newRefreshToken);
		return map;
	}

	@GetMapping("/getInfo")
	public Object getInfo(@RequestHeader("token") String token, String test) {
		if(!token.equals(this.token)) {
			throw new ServiceException("token 已过期请检查");
		}
		return test;
	}

}
