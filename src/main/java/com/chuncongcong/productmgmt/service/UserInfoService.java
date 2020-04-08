package com.chuncongcong.productmgmt.service;

import com.chuncongcong.productmgmt.model.vo.WxLoginVo;

/**
 * @author HU
 * @date 2019/12/19 14:21
 */

public interface UserInfoService {

	/**
	 * 用户登录
	 * @param userInfoVo 登录信息
	 * @return token值
	 */
	/*UserInfoVo login(UserInfoVo userInfoVo) throws Exception;*/
	/**
	 * 微信用户登录
	 * @param code
	 */
	WxLoginVo wxLogin(String code) throws Exception;
}
