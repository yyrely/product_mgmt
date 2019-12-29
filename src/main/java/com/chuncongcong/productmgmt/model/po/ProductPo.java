package com.chuncongcong.productmgmt.model.po;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

/**
 * @author HU
 * @date 2019/12/20 16:41
 */

@Data
@Table(name = "product")
public class ProductPo extends BaseFiled{

	@Id
	@KeySql(dialect = IdentityDialect.MYSQL, useGeneratedKeys = true)
	private Long productId;

	private Long userId;

	private Long categoryId;

	private String productNo;

	private String productName;

	private String productPic;

	private String productDesc;
}
