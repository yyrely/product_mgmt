package com.chuncongcong.productmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuncongcong.productmgmt.service.UserInfoService;

/**
 * @author HU
 * @date 2019/12/19 14:18
 */

@RestController
@RequestMapping("/api/user")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

	/*@PostMapping("/login")
	public Object login(@RequestBody @Validated UserInfoVo userInfoVo) throws Exception {
		return userInfoService.login(userInfoVo);
	}*/

	@PostMapping("/wx/login")
	public Object wxLogin(String code) throws Exception {
		return userInfoService.wxLogin(code);
	}

}
