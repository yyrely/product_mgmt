package com.chuncongcong.productmgmt.model.po;

import javax.persistence.Id;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

/**
 * @author HU
 * @date 2019/12/21 15:00
 */

@Data
public class ValuePo extends BaseFiled{

	@Id
	@KeySql(dialect = IdentityDialect.MYSQL, useGeneratedKeys = true)
	private Long valueId;

	private Long attributeId;

	private String valueName;
}
