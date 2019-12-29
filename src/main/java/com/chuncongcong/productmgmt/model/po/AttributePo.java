package com.chuncongcong.productmgmt.model.po;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

/**
 * @author HU
 * @date 2019/12/20 16:22
 */

@Data
@Table(name = "attribute")
public class AttributePo extends BaseFiled {

	@Id
	@KeySql(dialect = IdentityDialect.MYSQL, useGeneratedKeys = true)
	private Long attributeId;

	private Long categoryId;

	private String attributeName;
}
