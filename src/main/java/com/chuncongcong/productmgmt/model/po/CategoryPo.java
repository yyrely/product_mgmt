package com.chuncongcong.productmgmt.model.po;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

/**
 * @author HU
 * @date 2019/12/20 16:13
 */

@Data
@Table(name = "category")
public class CategoryPo extends BaseFiled{

	@Id
	@KeySql(dialect = IdentityDialect.MYSQL, useGeneratedKeys = true)
	private Long categoryId;

	private Long storeId;

	private String categoryName;
}
