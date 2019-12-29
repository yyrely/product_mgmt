package com.chuncongcong.productmgmt.model.po;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

/**
 * @author HU
 * @date 2019/12/20 16:45
 */

@Data
@Table(name = "product_sku")
public class SkuPo extends BaseFiled {

	@Id
	@KeySql(dialect = IdentityDialect.MYSQL, useGeneratedKeys = true)
	private Long skuId;

	private Long productId;

	private BigDecimal skuInPrice;

	private BigDecimal skuOutPrice;

	private Integer skuStock;

	private String skuDesc;
}
