package com.chuncongcong.productmgmt.model.po;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

/**
 * @author HU
 * @date 2019/12/22 15:53
 */

@Data
@Table(name = "product_attribute")
public class ProductAttributePo {

	@Id
	@KeySql(dialect = IdentityDialect.MYSQL, useGeneratedKeys = true)
	private Long id;

	private Long attributeId;

	private Long productId;
}
