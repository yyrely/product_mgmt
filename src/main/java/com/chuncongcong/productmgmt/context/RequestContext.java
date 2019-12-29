package com.chuncongcong.productmgmt.context;

import com.chuncongcong.productmgmt.model.po.UserInfoPo;

/**
 * @author HU
 * @date 2019/12/18 17:57
 */

public class RequestContext {

	public static ThreadLocal<UserInfoPo> USER_INFO = new InheritableThreadLocal<>();

	private RequestContext() {}

	public static UserInfoPo getUserInfo() {
		return USER_INFO.get();
	}

	public static void setUserInfo(UserInfoPo userInfo) {
		USER_INFO.set(userInfo);
	}

	public static void clear() {
		USER_INFO.remove();
	}
}
