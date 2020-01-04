package com.chuncongcong.productmgmt.model.po;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

/**
 * @author HU
 * @date 2019/12/22 17:17
 */

@Data
@Table(name = "purchase_log")
public class PurchaseLogPo extends BaseFiled{

	@Id
	@KeySql(dialect = IdentityDialect.MYSQL, useGeneratedKeys = true)
	private Long purchaseId;

	private Long storeId;

	private Long productId;

	private Long skuId;

	private Integer purchaseNums;

	private BigDecimal totalPrice;

	private LocalDateTime purchaseDate;

	private String purchaseUsername;
}
