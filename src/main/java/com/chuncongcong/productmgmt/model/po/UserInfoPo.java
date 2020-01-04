package com.chuncongcong.productmgmt.model.po;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

/**
 * @author HU
 * @date 2019/12/19 14:08
 */

@Data
@Table(name = "user_info")
public class UserInfoPo extends BaseFiled{

	@Id
	@KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
	private Long userId;

	private Long storeId;

	private String username;

	private String password;

	private String mobile;
}
