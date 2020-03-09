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
 * @date 2020/3/8 16:38
 */

@Data
@Table(name = "return_log")
public class ReturnLogPo extends BaseFiled{

	@Id
	@KeySql(dialect = IdentityDialect.MYSQL, useGeneratedKeys = true)
	private Long returnId;

	private Long storeId;

	private Long productId;

	private Long skuId;

	private Integer returnNums;

	private BigDecimal totalPrice;

	private LocalDateTime returnDate;

	private String returnUsername;
}
