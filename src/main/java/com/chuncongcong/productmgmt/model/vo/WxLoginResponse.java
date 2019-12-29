package com.chuncongcong.productmgmt.model.vo;

import lombok.Data;

/**
 * @author HU
 * @date 2019/12/29 15:14
 */

@Data
public class WxLoginResponse {

	private String openid;

	private String session_key;

	private String unionid;

	private Integer errcode;

	private String errmsg;
}
