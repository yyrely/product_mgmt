package com.chuncongcong.productmgmt.model.vo;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author HU
 * @date 2019/12/19 14:23
 */

@Data
public class UserInfoVo {

	private String username;

	@NotNull(message = "数据不能为null")
	private String password;

	@NotNull(message = "数据不能为null")
	private String mobile;

	private String token;

}
